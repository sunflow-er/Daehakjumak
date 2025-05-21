package com.masonk.daehakjumak.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.usecase.GetJumakInfoUseCase
import com.masonk.daehakjumak.domain.usecase.GetTableListUseCase
import com.masonk.daehakjumak.presentation.mapper.toPresentation
import com.masonk.daehakjumak.presentation.model.TableModel
import com.masonk.daehakjumak.presentation.model.uistate.JumakNameUiState
import com.masonk.daehakjumak.presentation.model.uistate.TableListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TableScreenViewModel(
    private val getJumakInfoUseCase: GetJumakInfoUseCase,
    private val getTableListUseCase: GetTableListUseCase,
) : ViewModel() {
    // 주문하기 위해 선택한 테이블 저장
    private val _orderedTable = MutableStateFlow<TableModel?>(null)
    val orderedTable = _orderedTable.asStateFlow()
    
    // 비우기하기 위해 지정한 테이블 저장
    private val _clearedTable = MutableStateFlow<TableModel?>(null)
    val clearedTable = _clearedTable.asStateFlow()

    // 서버에서 가져온 '주막이름' 정보를 상태(Loading/Success/Error)와 함께 저장
    private val _jumakNameUiState = MutableStateFlow(JumakNameUiState.DEFAULT)
    val jumakNameUiState = _jumakNameUiState.asStateFlow()

    // 서버에서 가져온 테이블 리스트 정보를 상태와 함께 저장
    private val _tableListUiState = MutableStateFlow(TableListUiState.DEFAULT)
    val tableListUiState = _tableListUiState.asStateFlow()


    // 초기화, 클래스가 인스턴스화될 때 실행
    init {
        getJumakName()
        fetchTableList()
    }

    // 주막 이름 정보 가져오기
    private fun getJumakName() {
        viewModelScope.launch {
            getJumakInfoUseCase()
                .onCompletion {
                    _jumakNameUiState.update { it.copy(isLoading = false) }
                }
                .collect({ result ->
                    when (result) {
                        is DataResource.Success -> {
                            _jumakNameUiState.update { it.copy(jumakName = result.data.toPresentation().name) }
                        }
                        is DataResource.Error -> {
                            _jumakNameUiState.update { it.copy(error = result.throwable) }
                        }
                        is DataResource.Loading -> {
                            _jumakNameUiState.update { it.copy(isLoading = true) }
                        }
                    }
                })
        }
    }

    // 테이블 리스트 정보 가져오기
    private fun fetchTableList() {
        viewModelScope.launch {
            getTableListUseCase()
                .onCompletion { // Flow 수집이 끝났을 때
                    _tableListUiState.update { it.copy(isLoading = false) } // 로딩 상태 종료
                }
                .collect({ result -> // Flow 구독 및 데이터 수신
                    when (result) {
                        is DataResource.Success -> {
                            _tableListUiState.update { it.copy(tableList = result.data.map { it.toPresentation() }) } // 테이블 리스트 업데이트
                        }

                        is DataResource.Error -> {
                            _tableListUiState.update { it.copy(error = result.throwable) } // 에러
                        }

                        is DataResource.Loading -> {
                            _tableListUiState.update { it.copy(isLoading = true) } // 로딩
                        }
                    }
                })
        }
    }

    fun clearTableOrderList() {
        // TODO 서버에 테이블 비우기 요청
    }

    // 비우기 할 테이블 지정
    fun startClearTable(table: TableModel) {
        _clearedTable.value = table
    }
    // 비우기 할 테이블 지정 초기화
    fun finishClearTable() {
        _clearedTable.value = null
    }

    // 주문할 테이블 지정
    fun startTableOrder(table: TableModel) {
        _orderedTable.value = table
    }
    // 주문할 테이블 지정 초기화
    fun finishTableOrder() {
        _orderedTable.value = null
    }
    

}
package com.masonk.daehakjumak.presentation.viewmodel

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masonk.daehakjumak.core.Coordinate
import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.usecase.AddTableUseCase
import com.masonk.daehakjumak.domain.usecase.GetTableListUseCase
import com.masonk.daehakjumak.domain.usecase.RemoveTableUseCase
import com.masonk.daehakjumak.domain.usecase.UpdateTableCoordUseCase
import com.masonk.daehakjumak.presentation.mapper.toDomain
import com.masonk.daehakjumak.presentation.mapper.toPresentation
import com.masonk.daehakjumak.presentation.model.TableModel
import com.masonk.daehakjumak.presentation.model.uistate.TableListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TableManagementScreenViewModel(
    private val getTableListUseCase: GetTableListUseCase, // 테이블 리스트 가져오기
    private val addTableUseCase: AddTableUseCase, // 테이블 추가하기
    private val removeTableUseCase: RemoveTableUseCase, // 테이블 삭제하기
    private val updateTableCoordUseCase: UpdateTableCoordUseCase, // 테이블 (최종) 좌표 업데이트하기
) : ViewModel() {
    // 테이블 리스트
    private val _tableListUiState = MutableStateFlow(TableListUiState.DEFAULT)
    val tableListUiState = _tableListUiState.asStateFlow()

    // ViewModel 생성 시
    init {
        fetchTableList()
    }

    // 테이블 리스트 정보 가져오기
    private fun fetchTableList() {
        viewModelScope.launch {
            getTableListUseCase()
                .onCompletion { // Flow 수집이 끝났을 때
                    _tableListUiState.update { it.copy(isLoading = false) } // 로딩 상태 종료
                }
                .collect({ dataResource -> // Flow 구독 및 데이터 수신
                    when (dataResource) {
                        is DataResource.Success -> {
                            _tableListUiState.update { it.copy(tableList = dataResource.data.map { it.toPresentation() }) } // 테이블 리스트 업데이트
                        }
                        is DataResource.Error -> {
                            _tableListUiState.update { it.copy(error = dataResource.throwable) } // 에러
                        }
                        is DataResource.Loading -> {
                            _tableListUiState.update { it.copy(isLoading = true) } // 로딩
                        }
                    }
                })
        }
    }

    // 테이블 추가
    fun addTable(table: TableModel) {
        viewModelScope.launch {
            // 로딩 상태로
            _tableListUiState.update { it.copy(isLoading = true) }

            // 서버 요청 및 결과 수신
            val result = addTableUseCase(table.toDomain())

            when (result) {
                is DataResource.Success -> { // 성공
                    // 기존 테이블 리스트에 테이블 추가
                    _tableListUiState.update { uiState ->
                        uiState.copy(
                            isLoading = false,
                            tableList = uiState.tableList + table
                        )
                    }
                }
                is DataResource.Error -> {
                    _tableListUiState.update { uiState ->
                        uiState.copy(
                            isLoading = false,
                            error = result.throwable
                        )
                    }
                }
                is DataResource.Loading -> {
                    _tableListUiState.update { uiState ->
                        uiState.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun removeTable(tableId: String) {
        viewModelScope.launch {
            // 로딩 상태로
            _tableListUiState.update { it.copy(isLoading = true) }

            // 서버 요청 및 결과 수신
            val result = removeTableUseCase(tableId)

            when (result) {
                is DataResource.Success -> {
                    _tableListUiState.update { uiState ->
                        uiState.copy(
                            isLoading = false,
                            tableList = uiState.tableList.filter { it.id != tableId }
                        )
                    }
                }
                is DataResource.Error -> {
                    _tableListUiState.update { uiState ->
                        uiState.copy(
                            isLoading = false,
                            error = result.throwable
                        )
                    }
                }
                is DataResource.Loading -> {
                    _tableListUiState.update { uiState ->
                        uiState.copy(isLoading = true)
                    }
                }
            }
        }

    }

    // 드래그 중, 테이블 임시 좌표 업데이트
    fun tempUpdateTableCoord(tableId: String, newCoord: Coordinate) {
        // UI에 즉시 반영
        _tableListUiState.update { uiState ->
            uiState.copy(
                tableList = uiState.tableList.map { table ->
                    if (table.id == tableId) table.copy(coordinate = newCoord) else table
                }
            )
        }
    }

    // 테이블 최종 좌표 업데이트
    fun updateTableCoord(tableId: String, newCoord: Coordinate, oldCoord: Coordinate) {
        viewModelScope.launch {
            // 서버 요청 및 결과 수신
            val result = updateTableCoordUseCase(tableId, newCoord)

            // 실패 시 이전 좌표로 롤백, 성공할 경우 별도 처리하지 않음
            if (result is DataResource.Error) {
                _tableListUiState.update { uiState ->
                    uiState.copy(
                        tableList = uiState.tableList.map { table ->
                            if (table.id == tableId) table.copy(coordinate = oldCoord) else table
                        },
                        error = result.throwable
                    )
                }
            }
        }
    }
}
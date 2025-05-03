package com.masonk.daehakjumak.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.usecase.SetJumakOpenStatusUseCase
import com.masonk.daehakjumak.domain.usecase.GetJumakInfoUseCase
import com.masonk.daehakjumak.presentation.mapper.toPresentation
import com.masonk.daehakjumak.presentation.model.uistate.JumakNameUiState
import com.masonk.daehakjumak.presentation.model.uistate.JumakOpenStatusUiState
import com.masonk.daehakjumak.presentation.model.uistate.JumakSalesInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainManagementScreenViewModel(
    private val getJumakInfoUseCase: GetJumakInfoUseCase,
    private val setJumakOpenStatusUseCase: SetJumakOpenStatusUseCase,
) : ViewModel() {

    // 주막 이름
    private val _jumakNameUiState = MutableStateFlow(JumakNameUiState.DEFAULT)
    val jumakNameUiState = _jumakNameUiState.asStateFlow()

    // 주막 오픈/마감 상태
    private val _jumakOpenStatusUiState = MutableStateFlow(JumakOpenStatusUiState.DEFAULT)
    val jumakOpenStatusUiState = _jumakOpenStatusUiState.asStateFlow()

    // 주막 오늘/총 매출 정보
    private val _jumakSalesInfoUiState = MutableStateFlow(JumakSalesInfoUiState.DEFAULT)
    val jumakSalesInfoUiState = _jumakSalesInfoUiState.asStateFlow()

    init {
        getJumakName()
        getJumakOpenStatus()
        fetchJumakSalesInfo()
    }

    // 주막 이름 정보 가져오기
    private fun getJumakName() {
        viewModelScope.launch {
            getJumakInfoUseCase()
                .onCompletion {
                    _jumakNameUiState.update { it.copy(isLoading = false) }
                }
                .collect({ dataResource ->
                    when (dataResource) {
                        is DataResource.Success -> {
                            _jumakNameUiState.update { it.copy(jumakName = dataResource.data.toPresentation().name) }
                        }
                        is DataResource.Error -> {
                            _jumakNameUiState.update { it.copy(error = dataResource.throwable) }
                        }
                        is DataResource.Loading -> {
                            _jumakNameUiState.update { it.copy(isLoading = true) }
                        }
                    }
                })
        }
    }
    
    // 주막 오픈/마감 상태 정보 가져오기
    private fun getJumakOpenStatus() {
        viewModelScope.launch {
            getJumakInfoUseCase()
                .onCompletion {
                    _jumakOpenStatusUiState.update { it.copy(isLoading = false) }
                }
                .collect({dataResource ->
                    when (dataResource) {
                        is DataResource.Success -> {
                            _jumakOpenStatusUiState.update { it.copy(isOpen = dataResource.data.toPresentation().isOpen) }
                        }
                        is DataResource.Error -> {
                            _jumakOpenStatusUiState.update { it.copy(error = dataResource.throwable) }
                        }
                        is DataResource.Loading -> {
                            _jumakOpenStatusUiState.update { it.copy(isLoading = true) }
                        }
                    }
                })
        }
    }

    // 주막 매출 정보 가져오기
    private fun fetchJumakSalesInfo() {
        viewModelScope.launch {
            getJumakInfoUseCase()
                .onCompletion {
                    _jumakSalesInfoUiState.update { it.copy(isLoading = false) }
                }
                .collect({dataResource ->
                    when (dataResource) {
                        is DataResource.Success -> {
                            _jumakSalesInfoUiState.update {
                                it.copy(
                                    todaySalesRevenue = dataResource.data.todaySalesRevenue,
                                    todaySalesList = dataResource.data.todaySalesList.map { it.toPresentation() },
                                    totalSalesRevenue = dataResource.data.totalSalesRevenue,
                                    totalSalesList = dataResource.data.totalSalesList.map { it.toPresentation() },
                                )
                            }
                        }
                        is DataResource.Error -> {
                            _jumakSalesInfoUiState.update { it.copy(error = dataResource.throwable) } // 에러
                        }

                        is DataResource.Loading -> {
                            _jumakSalesInfoUiState.update { it.copy(isLoading = true) } // 로딩
                        }
                    }
                })
        }
    }

    
    // 주막 오픈/마감 상태 변경
    fun setJumakOpenStatus(status: Boolean) {
        viewModelScope.launch {
            // 서버 요청
            setJumakOpenStatusUseCase(status)
                .onCompletion { // 완료 시
                    _jumakOpenStatusUiState.update { it.copy(isLoading = false) } // 로딩 종료
                } 
                .collect { result ->
                    when(result) {
                        is DataResource.Success -> getJumakOpenStatus() // 성공 시, 주막 정보 다시 요청
                        is DataResource.Error -> _jumakOpenStatusUiState.update { it.copy(error = result.throwable) } // 에러 갱신
                        is DataResource.Loading -> _jumakOpenStatusUiState.update { it.copy(isLoading = true) } // 로딩 표시
                    }
                }
        }
    }

}
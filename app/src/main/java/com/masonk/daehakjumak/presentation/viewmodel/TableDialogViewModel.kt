package com.masonk.daehakjumak.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masonk.daehakjumak.core.enums.OrderScreenType
import com.masonk.daehakjumak.core.enums.TableDialogStep
import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.usecase.GetMenuListUseCase
import com.masonk.daehakjumak.domain.usecase.GetOrderListUseCase
import com.masonk.daehakjumak.presentation.mapper.toPresentation
import com.masonk.daehakjumak.presentation.model.BasketItemModel
import com.masonk.daehakjumak.presentation.model.MenuModel
import com.masonk.daehakjumak.presentation.model.uistate.MenuBoardUiState
import com.masonk.daehakjumak.presentation.model.uistate.OrderHistoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TableDialogViewModel(
    private val getMenuListUseCase: GetMenuListUseCase,
    private val getOrderListUseCase: GetOrderListUseCase,
) : ViewModel() {
    // 테이블 다이얼로그 단계 저장 (ORDER/QR/COMPLETE)
    private val _tableDialogStep = MutableStateFlow<TableDialogStep>(TableDialogStep.ORDER)
    val tableDialogStep = _tableDialogStep.asStateFlow()

    // 주문 화면 유형 저장 (MENU/ORDER_HISTORY)
    private val _orderScreenType = MutableStateFlow<OrderScreenType>(OrderScreenType.MENU_BOARD)
    val orderScreenType = _orderScreenType.asStateFlow()

    // 메뉴판 타입 인덱스 저장 (FOOD(0)/DRINK(1))
    private val _selectedMenuTypeIndex = MutableStateFlow<Int>(0)
    val selectedMenuTypeIndex = _selectedMenuTypeIndex.asStateFlow()

    // 장바구니에 담긴 메뉴 리스트 저장
    private val _basketItemList = MutableStateFlow<List<BasketItemModel>>(emptyList())
    val basketItemList = _basketItemList.asStateFlow()

    // 서버에서 가져온 주막의 메뉴 리스트를 상태(Loading/Success/Error)와 함께 저장
    private val _menuBoardUiState = MutableStateFlow(MenuBoardUiState.DEFAULT)
    val menuBoardUiState = _menuBoardUiState.asStateFlow()

    // 서버에서 가져온 해당 테이블의 주문내역을 상태와 함께 저장
    private val _orderHistoryUiState = MutableStateFlow(OrderHistoryUiState.DEFAULT)
    val orderHistoryUiState = _orderHistoryUiState.asStateFlow()

    // 메뉴 리스트 가져오기
    private fun getMenuList() {
        viewModelScope.launch {
            getMenuListUseCase()
                .onCompletion {
                    _menuBoardUiState.update { it.copy(isLoading = false) }
                }
                .collect( {dataResource ->
                    when (dataResource) {
                        is DataResource.Success -> {
                            _menuBoardUiState.update { it.copy(menuList = dataResource.data.map { it.toPresentation() }) }
                        }
                        is DataResource.Error -> {
                            _menuBoardUiState.update { it.copy(error = dataResource.throwable) }
                        }
                        is DataResource.Loading -> {
                            _menuBoardUiState.update { it.copy(isLoading = true) }
                        }
                    }
                }
                )
        }
    }

    // 주문내역 가져오기
    private fun getOrderHistory() {
        viewModelScope.launch {
            getOrderListUseCase()
                .onCompletion {
                    _orderHistoryUiState.update { it.copy(isLoading = false) }
                }
                .collect( {dataResource ->
                    when (dataResource) {
                        is DataResource.Success -> {
                            _orderHistoryUiState.update { it.copy(orderHistory = dataResource.data.map { it.toPresentation() }) }
                        }
                        is DataResource.Error -> {
                            _orderHistoryUiState.update { it.copy(error = dataResource.throwable) }
                        }
                        is DataResource.Loading -> {
                            _orderHistoryUiState.update { it.copy(isLoading = true) }
                        }
                    }
                }
                )
        }
    }

    // 테이블 다이얼로그 단계 다음 단계로 이동
    fun moveDialogStepTo(step: TableDialogStep) {
        _tableDialogStep.value = step
    }

    // 주문 화면 유형 변경
    fun changeOrderScreenTypeTo(type: OrderScreenType) {
        _orderScreenType.value = type
    }

    // 메뉴판 메뉴 타입 변경
    fun changeMenuTypeIndexTo(index: Int) {
        _selectedMenuTypeIndex.value = index
    }

    // 장바구니에 메뉴 담기
    fun addMenuToBasket(menu: MenuModel) {
        val item = BasketItemModel(
            menuId = menu.id,
            menuName = menu.name,
            menuPrice = menu.price,
            count = 1,
        )

        // 장바구니 메뉴 리스트에 메뉴 추가
        _basketItemList.update { currentList ->
            currentList + item
        }
    }

    // 장바구니 아이템 삭제
    fun deleteBasketItem(index: Int) {
        _basketItemList.update { currentList ->
            currentList.toMutableList().also {
                if (index in it.indices) it.removeAt(index)
            }
        }
    }

    // 장바구니 아이템 개수 증가
    fun increaseBasketItemCount(index: Int) {
        _basketItemList.update { currentList ->
            currentList.toMutableList().also {
                if (index in it.indices) {
                    val item = it[index]
                    it[index] = item.copy(count = item.count + 1)
                }
            }
        }
    }

    // 장바구니 아이템 개수 감소
    fun decreaseBasketItemCount(index: Int) {
        _basketItemList.update { currentList ->
            currentList.toMutableList().also {
                if (index in it.indices) {
                    val item = it[index]
                    val newCount = (item.count - 1).coerceAtLeast(1)
                    it[index] = item.copy(count = newCount)
                }
            }
        }
    }


}
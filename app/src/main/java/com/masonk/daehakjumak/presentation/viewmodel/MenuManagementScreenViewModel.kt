package com.masonk.daehakjumak.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.masonk.daehakjumak.presentation.model.MenuModel
import com.masonk.daehakjumak.presentation.model.uistate.MenuBoardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MenuManagementScreenViewModel : ViewModel() {
    // 메뉴 타입
    private val _selectedMenuTypeIndex = MutableStateFlow<Int>(0)
    val selectedMenuTypeIndex = _selectedMenuTypeIndex.asStateFlow()

    // 메뉴 리스트
    private val _menuBoardUiState = MutableStateFlow(MenuBoardUiState.DEFAULT)
    val menuBoardUiState = _menuBoardUiState.asStateFlow()

    // 메뉴판 메뉴 타입 변경
    fun changeMenuTypeIndexTo(index: Int) {
        _selectedMenuTypeIndex.value = index
    }

    // 메뉴 삭제 요청
    fun deleteMenu(menu: MenuModel) {

    }

    // 메뉴 이미지 수정 요청
    fun editMenuImage() {

    }

    // 메뉴 이름 변경 요청
    fun editMenuName() {

    }

    // 메뉴 가격 변경 요청
    fun editMenuPrice() {

    }

    // 메뉴 품절 등록 요청
    fun soldOutMenu(menu: MenuModel) {

    }
}


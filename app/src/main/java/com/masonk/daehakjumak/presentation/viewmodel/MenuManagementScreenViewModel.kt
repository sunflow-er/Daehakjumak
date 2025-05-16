package com.masonk.daehakjumak.presentation.viewmodel

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import com.masonk.daehakjumak.core.enums.MenuAddEditDialogType
import com.masonk.daehakjumak.presentation.model.MenuModel
import com.masonk.daehakjumak.presentation.model.uistate.MenuBoardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

class MenuManagementScreenViewModel : ViewModel() {
    // 메뉴 타입
    private val _selectedEditMenuTypeIndex = MutableStateFlow<Int>(0)
    val selectedEditMenuTypeIndex = _selectedEditMenuTypeIndex.asStateFlow()

    // 메뉴 리스트
    private val _menuBoardUiState = MutableStateFlow(MenuBoardUiState.DEFAULT)
    val menuBoardUiState = _menuBoardUiState.asStateFlow()

    // 메뉴 추가 상태 확인
    private val _isAddingNewMenu = MutableStateFlow(false)
    val isAddingNewMenu = _isAddingNewMenu.asStateFlow()

    // 삭제 메뉴
    private val _deletedMenu = MutableStateFlow<MenuModel?>(null)
    val deletedMenu = _deletedMenu.asStateFlow()
    
    // 수정 메뉴
    private val _editedMenu = MutableStateFlow<Pair<MenuModel, MenuAddEditDialogType>?>(null)
    val editedMenu = _editedMenu.asStateFlow()

    // 메뉴판 메뉴 타입 변경
    fun changeMenuTypeIndexTo(index: Int) {
        _selectedEditMenuTypeIndex.value = index
    }

    // 메뉴 추가 시작
    fun startAddingNewMenu() {
        _isAddingNewMenu.value = true
    }
    // 메뉴 추가 완료/끝내기
    fun finishAddingNewMenu() {
        _isAddingNewMenu.value = false
    }

    // 메뉴 삭제 요청
    fun startDeleteMenu(menu: MenuModel) {
        _deletedMenu.value = menu
    }
    // 메뉴 삭제 완료
    fun finishDeleteMenu() {
        _deletedMenu.value = null
    }

    // 메뉴 이름/가격/사진 수정 시작
    fun startEditMenu(menu: MenuModel, menuEditType: MenuAddEditDialogType) {
        _editedMenu.value = menu to menuEditType
    }
    // 메뉴  이름/가격/사진 수정 완료
    fun finishEditMenu() {
        _editedMenu.value = null
    }

    // 메뉴 품절 등록
    fun registerSoldOut(menu: MenuModel) {

    }

    fun editMenuName(menu: MenuModel, newName: String) {

    }

    fun editMenuPrice(menu: MenuModel, newPrice: Int) {

    }

    fun editMenuImage(menu: MenuModel, newImage: String) {

    }

}


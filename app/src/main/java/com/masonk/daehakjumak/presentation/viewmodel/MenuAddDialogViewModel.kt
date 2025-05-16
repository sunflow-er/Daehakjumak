package com.masonk.daehakjumak.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.masonk.daehakjumak.core.enums.MenuAddEditDialogType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MenuAddDialogViewModel() : ViewModel() {
    // 메뉴 추가 단계 (이름, 가격, 사진)
    private val _menuAddDialogStep = MutableStateFlow(MenuAddEditDialogType.NAME)
    val menuAddDialogStep = _menuAddDialogStep.asStateFlow()

    // 다음 단계로 이동
    fun moveDialogStepTo(step: MenuAddEditDialogType) {
        _menuAddDialogStep.value = step
    }

    // 이름 추가/저장
    fun addMenuName(name: String) {

    }

    // 가격 추가/저장
    fun addMenuPrice(price: Int) {

    }

    // 사진 추가/저장
    fun addMenuImage(image: String) {

    }

    // 메뉴 추가 프로세스 완료
    fun completeMenuAdd() {

    }
}
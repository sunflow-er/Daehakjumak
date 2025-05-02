package com.masonk.daehakjumak.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.masonk.daehakjumak.core.enums.ManagerNavigationTarget
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ManagerScreenViewModel : ViewModel() {
    // 네비게이션 타겟 (관리자 화면, 테이블 관리, 메뉴 관리, 계좌 관리)
    private val _navigationTarget = MutableStateFlow(ManagerNavigationTarget.MAIN)
    val navigationTarget = _navigationTarget.asStateFlow()

    // 테이블 관리 클릭
    fun onClickTableManagement() {
        _navigationTarget.value = ManagerNavigationTarget.TABLE_MANAGEMENT
    }

    // 메뉴 관리 클릭
    fun onClickMenuManagement() {
        _navigationTarget.value = ManagerNavigationTarget.MENU_MANAGEMENT
    }

    // 계좌 관리 클릭
    fun onClickAccountManagement() {
        _navigationTarget.value = ManagerNavigationTarget.ACCOUNT_MANGEMENT
    }

    // 뒤로가기 클릭
    fun onClickBackToMainManagement() {
        _navigationTarget.value = ManagerNavigationTarget.MAIN // 메인 관리 화면
    }
}
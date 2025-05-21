package com.masonk.daehakjumak.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.masonk.daehakjumak.core.MainNavScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel : ViewModel() {
    // 네비게이션 레일에서 선택된 화면
    private val _selectedMainNavScreen = MutableStateFlow(MainNavScreen.TableScreen.route)
    val selectedMainNavScreen = _selectedMainNavScreen.asStateFlow()

    // 네비게이션 레일 선택 화면 변경
    fun selectMainNavScreen(route: String) {
        _selectedMainNavScreen.value = route
    }
}
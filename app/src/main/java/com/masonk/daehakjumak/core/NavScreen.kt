package com.masonk.daehakjumak.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.ui.graphics.vector.ImageVector

// 메인 화면 네비게이션 화면 목록
sealed class MainNavScreen(val route: String, val title: String, val icon: ImageVector) {
    object TableScreen : MainNavScreen("table", "테이블", Icons.Default.CheckCircle)
    object OrderScreen : MainNavScreen("order", "주문", Icons.Default.CheckCircle)
    object WaitingScreen : MainNavScreen("waiting", "대기", Icons.Default.CheckCircle)
    object ManagerScreen : MainNavScreen("manager_root", "관리자", Icons.Default.CheckCircle)

    companion object {
        val navScreenList = listOf(TableScreen, OrderScreen, WaitingScreen, ManagerScreen)
    }
}

// 관리자 화면 네비게이션 화면 목록
sealed class ManagerNavScreen(val route: String) {
    object MainManagementScreen : ManagerNavScreen("manager_main")
    object TableManagementScreen : ManagerNavScreen("table_management")
    object MenuManagementScreen : ManagerNavScreen("menu_management")
    object AccountManagementScreen : ManagerNavScreen("account_management")
}
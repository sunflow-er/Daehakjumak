package com.masonk.daehakjumak.ui.manager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.masonk.daehakjumak.core.ManagerNavScreen
import com.masonk.daehakjumak.core.enums.ManagerNavigationTarget
import com.masonk.daehakjumak.presentation.viewmodel.ManagerScreenViewModel

@Composable
fun ManagerScreen(managerScreenViewModel: ManagerScreenViewModel, managerNavController: NavHostController, modifier: Modifier) {
    val navigationTarget by managerScreenViewModel.navigationTarget.collectAsState() // 네비게이션 해야할 화면

    // 네비게이션 타겟에 따라 화면 갈아끼우기
    when (navigationTarget) {
        ManagerNavigationTarget.MAIN -> { managerNavController.navigate(ManagerNavScreen.MainManagementScreen.route)}
        ManagerNavigationTarget.TABLE_MANAGEMENT -> { managerNavController.navigate(ManagerNavScreen.TableManagementScreen.route)}
        ManagerNavigationTarget.MENU_MANAGEMENT -> {managerNavController.navigate(ManagerNavScreen.MenuManagementScreen.route)}
        ManagerNavigationTarget.ACCOUNT_MANGEMENT -> {managerNavController.navigate(ManagerNavScreen.AccountManagementScreen.route)}
    }

    // 네비게이션 호스트
    ManagerNavHost(managerNavController, modifier)
}

// 관리자 화면 네비게이션 호스트
@Composable
fun ManagerNavHost(managerNavController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = managerNavController,
        startDestination = ManagerNavScreen.MainManagementScreen.route,
        modifier = modifier
    ) {
        composable(ManagerNavScreen.MainManagementScreen.route) {
            MainManagementScreen()
        }
        composable(ManagerNavScreen.TableManagementScreen.route) {
            TableManagementScreen()
        }
        composable(ManagerNavScreen.MenuManagementScreen.route) {
            MenuManagementScreen()
        }
        composable(ManagerNavScreen.AccountManagementScreen.route) {
            AccountManagementScreen()
        }
    }
}

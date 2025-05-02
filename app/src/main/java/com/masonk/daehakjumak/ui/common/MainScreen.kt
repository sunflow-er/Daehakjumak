package com.masonk.daehakjumak.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.masonk.daehakjumak.core.MainNavScreen
import com.masonk.daehakjumak.ui.manager.ManagerScreen
import com.masonk.daehakjumak.ui.order.OrderScreen
import com.masonk.daehakjumak.ui.table.TableScreen
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.waiting.WaitingScreen

@Composable
fun MainScreen() {
    val mainNavController = rememberNavController()
    val mainNavScreenList = MainNavScreen.navScreenList
    var selectedMainNavScreen by remember { mutableStateOf(MainNavScreen.TableScreen.route) }

    Row(modifier = Modifier.fillMaxSize()) {
        // 네비게이션 레일
        NavigationRail(
            containerColor = Color.LightGray,
            modifier = Modifier
                .weight(134f)
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                mainNavScreenList.forEach{ mainNavScreen ->
                    NavigationRailItem(
                        selected = selectedMainNavScreen == mainNavScreen.route,
                        onClick = {
                            selectedMainNavScreen = mainNavScreen.route
                            mainNavController.navigate(mainNavScreen.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(mainNavScreen.icon, contentDescription = mainNavScreen.title) },
                        label = { Text(mainNavScreen.title) }
                    )

                }
            }
        }

        // 네비게이션 호스트
        Box(modifier = Modifier
            .weight(1551f)
            .fillMaxHeight()) {
            MainNavHost(
                mainNavController = mainNavController,
                modifier = Modifier
                    .fillMaxHeight()
            )
        }

    }
}

// 메인 화면 네비게이션 호스트
@Composable
fun MainNavHost(
    mainNavController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = mainNavController,
        startDestination = MainNavScreen.TableScreen.route,
        modifier = modifier,
    ) {
        composable(MainNavScreen.TableScreen.route) { TableScreen() }
        composable(MainNavScreen.OrderScreen.route) { OrderScreen() }
        composable(MainNavScreen.WaitingScreen.route) { WaitingScreen() }
        composable(MainNavScreen.ManagerScreen.route) { // 관리자 화면 중첩 NavHost
            val managerNavController = rememberNavController()
            ManagerScreen(managerScreenViewModel = ManagerScreenViewModle() ,managerNavController = managerNavController, modifier = modifier)
        }
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=1685dp,height=1053dp,dpi=160",
)
@Composable
fun Preview() {
    DaehakjumakTheme {
        MainScreen()
    }
}
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.masonk.daehakjumak.presentation.viewmodel.MainScreenViewModel
import com.masonk.daehakjumak.ui.manager.ManagerScreen
import com.masonk.daehakjumak.ui.order.OrderScreen
import com.masonk.daehakjumak.ui.table.TableScreen
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.theme.LabelDisabled2
import com.masonk.daehakjumak.ui.theme.LabelNeutral2
import com.masonk.daehakjumak.ui.theme.LabelStrong
import com.masonk.daehakjumak.ui.waiting.WaitingScreen

@Composable
fun MainScreen(mainScreenViewModel: MainScreenViewModel) {
    val mainNavController = rememberNavController() // 메인 네비게이션 컨트롤러
    val mainNavScreenList = MainNavScreen.navScreenList // 메인 네비게이션 화면 리스트

    val selectedMainNavScreen by mainScreenViewModel.selectedMainNavScreen.collectAsState() // 네비게이션 레일에서 선택된 화면

    Row(modifier = Modifier.fillMaxSize()) {
        // 네비게이션 레일
        NavigationRail(
            containerColor = LabelNeutral2,
            modifier = Modifier
                .weight(134f)
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                mainNavScreenList.forEach { mainNavScreen ->
                    // 선택된 화면인지 판단
                    val selected = (selectedMainNavScreen == mainNavScreen.route)

                    NavigationRailItem(
                        selected = selected,
                        onClick = {
                            mainScreenViewModel.selectMainNavScreen(mainNavScreen.route) // 해당화면으로 선택/변경
                            mainNavController.navigate(mainNavScreen.route) { // 해당화면으로 이동
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                mainNavScreen.icon,
                                contentDescription = mainNavScreen.title,
                                tint = if (selected) LabelStrong else LabelDisabled2
                            )
                        },
                        label = {
                            Text(
                                text = mainNavScreen.title,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (selected) LabelStrong else LabelDisabled2
                            )
                        }
                    )

                }
            }
        }

        // 네비게이션 호스트
        Box(
            modifier = Modifier
                .weight(1551f)
                .fillMaxHeight()
        ) {
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
            ManagerScreen(
                managerScreenViewModel =,
                managerNavController = managerNavController,
                modifier = modifier
            )
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
         // MainScreen()
    }
}
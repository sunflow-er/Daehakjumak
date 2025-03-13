package com.masonk.daehakjumak.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.masonk.daehakjumak.ui.manager.ManagerScreen
import com.masonk.daehakjumak.ui.order.OrderScreen
import com.masonk.daehakjumak.ui.table.TableScreen
import com.masonk.daehakjumak.ui.waiting.WaitingScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navScreenList = NavScreen.navScreens
    var selectedNavScreen by remember { mutableStateOf(NavScreen.TableScreen.route) }

    Row(modifier = Modifier.fillMaxSize()) {

        NavigationRail(
            containerColor = Color.LightGray,
            modifier = Modifier.width(80.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                navScreenList.forEachIndexed { index, navScreen ->
                    NavigationRailItem(
                        selected = selectedNavScreen == navScreen.route,
                        onClick = {
                            selectedNavScreen = navScreen.route
                            navController.navigate(navScreen.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(navScreen.icon, contentDescription = navScreen.title) },
                        label = { Text(navScreen.title) }
                    )

                    if (index == 2) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            modifier = Modifier.padding(16.dp, 8.dp),
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        NavHost(navController = navController, startDestination = selectedNavScreen) {
            composable(NavScreen.TableScreen.route) { TableScreen() }
            composable(NavScreen.OrderScreen.route) { OrderScreen() }
            composable(NavScreen.WaitingScreen.route) { WaitingScreen() }
            composable(NavScreen.ManagerScreen.route) { ManagerScreen() }
        }
    }
}

sealed class NavScreen(val route: String, val title: String, val icon: ImageVector) {
    object TableScreen : NavScreen("table", "테이블", Icons.Default.CheckCircle)
    object OrderScreen : NavScreen("order", "주문", Icons.Default.CheckCircle)
    object WaitingScreen : NavScreen("waiting", "대기", Icons.Default.CheckCircle)
    object ManagerScreen : NavScreen("manager", "관리자", Icons.Default.CheckCircle)

    companion object {
        val navScreens = listOf(TableScreen, OrderScreen, WaitingScreen, ManagerScreen)
    }
}
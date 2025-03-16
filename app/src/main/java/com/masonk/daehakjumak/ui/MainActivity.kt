package com.masonk.daehakjumak.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.masonk.daehakjumak.ui.common.MainScreen
import com.masonk.daehakjumak.ui.common.NavScreen
import com.masonk.daehakjumak.ui.manager.ManagerScreen
import com.masonk.daehakjumak.ui.order.OrderScreen
import com.masonk.daehakjumak.ui.table.TableScreen
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import com.masonk.daehakjumak.ui.waiting.WaitingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaehakjumakTheme {
                DaehakjumakApp()
            }
        }
    }
}

@Composable
fun DaehakjumakApp() {
    // 로그인 상태 확인

    // 로그인 되어있지 않으면 LoginActivity로 이동

    // 로그인 되어있으면 MainScreen 띄우기
    MainScreen()
}

@Preview(
    showSystemUi = true,
    device = "spec:width=1685dp,height=1053dp,dpi=160",
)
@Composable
fun Preview() {
    DaehakjumakTheme {
        DaehakjumakApp()
    }
}
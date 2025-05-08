package com.masonk.daehakjumak.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.masonk.daehakjumak.ui.login.LoginScreen
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import androidx.navigation.compose.composable
import com.masonk.daehakjumak.ui.table.TableScreen

@OptIn(ExperimentalMaterial3Api::class)
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DaehakjumakTheme {
                val navController = rememberNavController()

                // LoginScreen에 NavHostController를 전달하지 않고
                // NavHost에서 직접 navigation을 관리
                NavHost(navController = navController, startDestination = "loginScreen") {
                    composable("loginScreen") {
                        LoginScreen(navController = navController) // navController 전달
                    }
                    composable("tableScreen") {
                        TableScreen()
                    }
                }
            }
        }
    }
}

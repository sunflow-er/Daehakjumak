package com.masonk.daehakjumak.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.masonk.daehakjumak.ui.login.LoginScreen
import com.masonk.daehakjumak.ui.login.SplashScreen
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DaehakjumakTheme {
                // Splash 표시 여부
                var showSplash by remember { mutableStateOf(true) }

                if (showSplash) {
                    SplashScreen(onSplashFinished = { showSplash = false })
                } else {
                    LoginScreen()
                }
            }
        }
    }
}
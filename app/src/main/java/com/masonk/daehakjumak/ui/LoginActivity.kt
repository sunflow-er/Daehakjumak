package com.masonk.daehakjumak.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.masonk.daehakjumak.ui.login.LoginScreen
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DaehakjumakTheme {
                LoginScreen()
            }
        }
    }
}
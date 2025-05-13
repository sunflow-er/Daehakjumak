package com.masonk.daehakjumak.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme
import androidx.navigation.compose.composable
import com.masonk.daehakjumak.ui.login.LoginScreen
import com.masonk.daehakjumak.ui.table.TableScreen

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DaehakjumakTheme {
                LoginScreen()
            }
        }
    }

//    fun navigateToMainActivity() {
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
}

package com.masonk.daehakjumak.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.masonk.daehakjumak.ui.common.MainScreen
import com.masonk.daehakjumak.ui.theme.DaehakjumakTheme

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
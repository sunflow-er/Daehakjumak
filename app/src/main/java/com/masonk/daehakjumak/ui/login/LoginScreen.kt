package com.masonk.daehakjumak.ui.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kakao.sdk.user.UserApiClient
import com.masonk.daehakjumak.R
import kotlinx.coroutines.delay

@Composable
fun LoginScreen() {
    // 스플래시 표시 여부
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen(
            onSplashFinished = {
                showSplash = false
            }
        )
    } else { // 로그인 UI
        Box {
            KakaoLoginScreen()
        }
    }
}

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        onSplashFinished()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.beer),
                contentDescription = "App Logo",
                modifier = Modifier.size(160.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "DaehakJumak App",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun KakaoLoginScreen() {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            kakaoLogin(context)
        }) {
            Text(text = "카카오 로그인하기")
        }
        Button(onClick = {
            kakaoLogout(context)
        }) {
            Text(text = "카카오 로그아웃하기")
        }
    }
}

// 카카오 로그인 로직
fun kakaoLogin(context: android.content.Context) {
    // 카카오톡 설치 여부 확인
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        // 카카오톡으로 로그인
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Log.e("KakaoLogin", "카카오톡 로그인 실패", error)
                // 카카오톡 앱이 없는 등 실패 시에는 계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(context) { accountToken, accountError ->
                    if (accountError != null) {
                        Log.e("KakaoLogin", "카카오 계정 로그인도 실패", accountError)
                    } else if (accountToken != null) {
                        Log.i("KakaoLogin", "카카오 계정 로그인 성공 : ${accountToken.accessToken}")
                        // 로그인 성공 시
                    }
                }
            } else if (token != null) {
                Log.i("KakaoLogin", "카카오톡 로그인 성공 : ${token.accessToken}")
                // 로그인 성공 시
            }
        }
    } else {
        // 카카오 계정으로 로그인
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                Log.e("KakaoLogin", "카카오 계정 로그인 실패", error)
            } else if (token != null) {
                Log.i("KakaoLogin", "카카오 계정 로그인 성공 : ${token.accessToken}")
                // 로그인 성공 시
            }
        }
    }
}

// 카카오 로그아웃 로직
fun kakaoLogout(context: android.content.Context) {
    UserApiClient.instance.logout { error ->
        if (error != null) {
            Log.e("KakaoLogin", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
        } else {
            Log.i("KakaoLogin", "로그아웃 성공. SDK에서 토큰 삭제됨")
        }
    }
}
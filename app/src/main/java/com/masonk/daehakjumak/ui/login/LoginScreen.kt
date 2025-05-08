package com.masonk.daehakjumak.ui.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
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
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.beer),
                contentDescription = "App Logo",
                modifier = Modifier.size(160.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "대학주막 캐치프레이즈", // 캐치 프레이즈 내용 변경
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "대학주막은 카카오계정으로만 로그인할 수 있습니다.",
                fontSize = 20.sp,
                color = Color.White
            )
            Image( // 이거 어떻게 조정하지..
                painter = painterResource(id = R.drawable.kakao_login),
                contentDescription = "카카오 로그인 버튼",
                modifier = Modifier
                    .clickable {
                        kakaoLogin(context)
                    }
                    .size(500.dp)
            )
        }
    }
}

fun kakaoLogin(context: android.content.Context) {
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Log.e("KakaoLogin", "카카오톡 로그인 실패", error)
                UserApiClient.instance.loginWithKakaoAccount(context) { accountToken, accountError ->
                    if (accountError != null) {
                        Log.e("KakaoLogin", "카카오 계정 로그인도 실패", accountError)
                    } else if (accountToken != null) {
                        Log.i("KakaoLogin", "카카오 계정 로그인 성공 : ${accountToken.accessToken}")
                    }
                }
            } else if (token != null) {
                Log.i("KakaoLogin", "카카오톡 로그인 성공 : ${token.accessToken}")
            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                Log.e("KakaoLogin", "카카오 계정 로그인 실패", error)
            } else if (token != null) {
                Log.i("KakaoLogin", "카카오 계정 로그인 성공 : ${token.accessToken}")
            }
        }
    }
}


fun kakaoLogout(context: android.content.Context) {
    UserApiClient.instance.logout { error ->
        if (error != null) {
            Log.e("KakaoLogin", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
        } else {
            Log.i("KakaoLogin", "로그아웃 성공. SDK에서 토큰 삭제됨")
        }
    }
}
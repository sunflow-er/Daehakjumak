package com.masonk.daehakjumak.ui.login

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.kakao.sdk.user.UserApiClient
import com.masonk.daehakjumak.R
import com.masonk.daehakjumak.remote.api.RetrofitClient
import com.masonk.daehakjumak.data.model.KakaoLoginRequest
import com.masonk.daehakjumak.data.model.KakaoLoginResponse
import com.masonk.daehakjumak.data.model.TokenManager
import com.masonk.daehakjumak.ui.LoginActivity
import com.masonk.daehakjumak.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(160.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "정신없는 축제 주막, 이젠 대학주막으로 똑똑하게!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "대학주막은 카카오계정으로만 로그인할 수 있습니다.",
                fontSize = 20.sp,
                color = Color.White
            )
            Image(
                painter = painterResource(id = R.drawable.kakao_login),
                contentDescription = "카카오 로그인 버튼",
                modifier = Modifier
                    .clickable {
                        kakaoLogin(context)
                    }
                    .size(250.dp)
            )
        }
    }
}

fun kakaoLogin(context: Context) {
    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        if (error != null) {
            Log.e("KakaoLogin", "카카오톡 로그인 실패", error)
            UserApiClient.instance.loginWithKakaoAccount(context) { accountToken, accountError ->
                if (accountError != null) {
                    Log.e("KakaoLogin", "카카오 계정 로그인도 실패", accountError)
                } else if (accountToken != null) {
                    Log.i("KakaoLogin", "카카오 계정 로그인 성공 : ${accountToken.accessToken}")
                    sendAccessTokenToServer(accountToken.accessToken, context)
                }
            }
        } else if (token != null) {
            Log.i("KakaoLogin", "카카오톡 로그인 성공 : ${token.accessToken}")
            sendAccessTokenToServer(token.accessToken, context)
        }
    }
}

fun sendAccessTokenToServer(accessToken: String, context: Context) {
    val scope = (context as? LoginActivity)?.lifecycleScope
        ?: CoroutineScope(Dispatchers.IO)

    scope.launch {
        try {
            // 로그: 액세스 토큰 출력
            Log.d("KakaoLogin", "Sending access token to server: $accessToken")

            val body = KakaoLoginRequest(accessToken)
            // 로그: 요청 바디 출력
            Log.d("KakaoLogin", "Request body: $body")

            val resp = RetrofitClient.apiService
                .kakaoLogin(body)

            // 로그: 서버 응답 상태 코드 출력
            Log.d("KakaoLogin", "Response code: ${resp.code}")

            if (resp.code == 0) {
                TokenManager(context).saveTokens(
                    resp.value.accessToken,
                    resp.value.refreshToken
                )

                // 메인 스레드에서 화면 전환
                withContext(Dispatchers.Main) {
                    context.startActivity(
                        Intent(context, MainActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                    Intent.FLAG_ACTIVITY_NEW_TASK)
                    )
                }
            } else {
                Log.e("KakaoLogin", "로그인 실패: ${resp.message}")
            }
        } catch (e: Exception) {
            Log.e("KakaoLogin", "백엔드 로그인 요청 실패", e)
        }
    }
}
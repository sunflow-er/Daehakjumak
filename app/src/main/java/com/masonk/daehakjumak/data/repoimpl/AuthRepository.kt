package com.masonk.daehakjumak.data.repoimpl

import com.masonk.daehakjumak.data.model.KakaoLoginRequest
import com.masonk.daehakjumak.remote.api.AuthApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi
) {
    /** 카카오 accessToken → 우리 서버 토큰 교환 */
    suspend fun exchangeKakaoToken(kakaoAccess: String) =
        withContext(Dispatchers.IO) {
            val body       = KakaoLoginRequest(kakaoAccess)

            api.kakaoLogin(body)
        }
}

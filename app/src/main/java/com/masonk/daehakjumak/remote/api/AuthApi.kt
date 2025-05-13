package com.masonk.daehakjumak.remote.api

import com.masonk.daehakjumak.data.model.KakaoLoginRequest
import com.masonk.daehakjumak.data.model.KakaoLoginResponse
import com.masonk.daehakjumak.data.model.KakaoRefreshRequest
import com.masonk.daehakjumak.data.model.KakaoRefreshResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/auth/kakao")
    suspend fun kakaoLogin(
        @Body req: KakaoLoginRequest
    ): KakaoLoginResponse

    @POST("/api/auth/refresh")
    suspend fun kakaoRefresh(
        @Header("Authorization") authorization: String,
        @Body req: KakaoRefreshRequest
    ): KakaoRefreshResponse
}
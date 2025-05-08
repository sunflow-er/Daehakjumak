package com.masonk.daehakjumak.remote.api

import com.masonk.daehakjumak.data.model.KakaoLoginRequest
import com.masonk.daehakjumak.data.model.KakaoLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/v1/auth/kakao")
    suspend fun kakaoLogin(@Body body: KakaoLoginRequest): KakaoLoginResponse
}
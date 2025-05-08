package com.masonk.daehakjumak.data.model

data class KakaoLoginResponse(
    val code: Int,
    val message: String,
    val value: TokenValueDto
)

data class TokenValueDto(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int
)


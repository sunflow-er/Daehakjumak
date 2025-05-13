package com.masonk.daehakjumak.data.model

data class KakaoLoginResponse(
    val code: Int,
    val message: String,
    val value: TokenValueDto
)

package com.masonk.daehakjumak.data.model

data class KakaoRefreshResponse(
    val code: Int,
    val message: String,
    val value: TokenValueDto
)
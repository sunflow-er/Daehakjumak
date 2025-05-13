package com.masonk.daehakjumak.data.model

data class TokenValueDto(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int
)

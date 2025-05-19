package com.masonk.daehakjumak.data.model

data class CreateWaitingRequest(
    val jumakId: Long,
    val nop: Int,
    val phoneNum: String
)
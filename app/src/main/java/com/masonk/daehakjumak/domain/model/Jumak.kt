package com.masonk.daehakjumak.domain.model

// 주막 정보
data class Jumak(
    val id: String, // id
    var name: String, // 주막 이름
    var accountNumber: String, // 계좌번호
    var isOpen: Boolean // 오픈/마감 여부
    // TODO 추가/수정/보완
)

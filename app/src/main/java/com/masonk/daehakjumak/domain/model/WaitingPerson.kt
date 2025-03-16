package com.masonk.daehakjumak.domain.model

// 대기자
data class WaitingPerson(
    var listNumber: Int, // 명단 번호
    val WaitingNumber: Int, // 대기 번호, id
    var groupSize: Int, // 인원 수
    var phoneNumber: String, // 휴대폰 번호
    val registerAt: Long, // 등록 시간
)

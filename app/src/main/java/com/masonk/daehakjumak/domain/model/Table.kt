package com.masonk.daehakjumak.domain.model

// 테이블
data class Table(
    val id: Int, // 테이블 절대 번호 (key/index/id)
    var tableNumber: Int, // 테이블 상대 번호
    var isAvailable: Boolean, // 테이블 활성 상태
    val orderList: MutableList<Order>, // 해당 테이블 주문 리스트
)

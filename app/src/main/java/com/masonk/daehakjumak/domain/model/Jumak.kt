package com.masonk.daehakjumak.domain.model

// 주막 정보
data class Jumak(
    val id: String, // id
    var name: String, // 주막 이름
    var isOpen: Boolean, // 오픈/마감 여부
    val todaySalesRevenue: Int, // 오늘 매출 금액
    val todaySalesList: List<Order>, // 오늘 매출 리스트
    val totalSalesRevenue: Int, // 총매출 금액
    val totalSalesList: List<Order>, // 총매출 리스트
    var accountNumber: String, // 계좌번호
)

package com.masonk.daehakjumak.presentation.model

import com.masonk.daehakjumak.domain.model.Jumak
import com.masonk.daehakjumak.domain.model.Order

data class JumakModel(
    val id: String, // id
    val name: String, // 주막 이름
    val accountNumber: String, // 계좌번호
    val isOpen: Boolean, // 오픈/마감 여부
    val todaySalesRevenue: Int, // 오늘 매출 금액
    val todaySalesList: List<OrderModel>, // 오늘 매출 리스트
    val totalSalesRevenue : Int, // 총매출 금액
    val totalSalesList: List<OrderModel>, // 총매출 리스트
    // TODO 추가/수정/보완
)

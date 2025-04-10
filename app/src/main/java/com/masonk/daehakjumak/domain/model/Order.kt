package com.masonk.daehakjumak.domain.model

import com.masonk.daehakjumak.core.enums.MenuType
import com.masonk.daehakjumak.core.enums.OrderStatus

// 주문
data class Order(
    val id: String, // 주문 id
    var status: OrderStatus, // 주문 상태
    val time: Long, // 주문 시간
    val tableId: String, // 테이블 id
    val menuType: MenuType, // 메뉴 분류
    var menuName: String, // 메뉴 이름
    var menuPrice: kotlin.Int, // 메뉴 가격
)

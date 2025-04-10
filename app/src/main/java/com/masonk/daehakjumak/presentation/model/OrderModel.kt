package com.masonk.daehakjumak.presentation.model

import com.masonk.daehakjumak.core.enums.MenuType
import com.masonk.daehakjumak.core.enums.OrderStatus

data class OrderModel(
    val id: String, // 주문 id
    var status: OrderStatus, // 주문 상태
    val time: Long, // 주문 시간
    val tableId: String, // 테이블 절대 번호
    val menuType: MenuType, // 메뉴 분류
    var menuName: String, // 메뉴 이름
    var menuPrice: Int, // 메뉴 가격
)

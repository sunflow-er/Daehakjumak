package com.masonk.daehakjumak.presentation.model

import com.masonk.daehakjumak.domain.model.MenuCore
import com.masonk.daehakjumak.core.OrderStatus

data class OrderModel(
    val id: String, // 주문 id
    var status: OrderStatus, // 주문 상태
    val time: Long, // 주문 시간
    val tableId: Int, // 테이블 절대 번호
    val menuCore: MenuCoreModel // 주문한 메뉴 핵심 정보 (분류, 이름, 가격)
)

package com.masonk.daehakjumak.presentation.model

import com.masonk.daehakjumak.core.MenuType
import com.masonk.daehakjumak.core.OrderStatus
import com.masonk.daehakjumak.domain.model.Order

data class TableModel(
    val id: Int, // 테이블 절대 번호 (key/index/id)
    var tableNumber: Int, // 테이블 상대 번호
    var isAvailable: Boolean, // 테이블 활성 상태
    val orderList: MutableList<OrderModel>, // 해당 테이블 주문 리스트
)

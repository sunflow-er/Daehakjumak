package com.masonk.daehakjumak.presentation.model

import com.masonk.daehakjumak.core.Coordinate

data class TableModel(
    val id: String, // id
    var number: String, // 테이블 번호 (테이블 추가 및 삭제에 따라 변함)
    var coordinate: Coordinate, // 테이블 좌표(x, y), 중심 위치
    val orderList: List<OrderModel>, // 해당 테이블 주문 리스트
)

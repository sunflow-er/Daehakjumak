package com.masonk.daehakjumak.domain.model

import com.masonk.daehakjumak.core.Coordinate

// 테이블
data class Table(
    val id: String, // id
    var number: Int, // 테이블 번호 (테이블 추가 및 삭제에 따라 변함)
    var coordinate: Coordinate, // 테이블 좌표(x, y), 중심 위치
    val orderList: MutableList<Order>, // 해당 테이블 주문 리스트
)

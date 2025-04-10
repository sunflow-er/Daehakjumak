package com.masonk.daehakjumak.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// 기본: CircleShape, RectangleShape(Default)
val Shapes = Shapes(
    extraSmall = RoundedCornerShape(5.dp), // 관리자 페이지 '총매출', '메뉴별 매출'
    small = RoundedCornerShape(8.dp), // 주문 상태 표시 칩
    medium = RoundedCornerShape(10.dp), // 거의 대부분
    large = RoundedCornerShape(20.dp), // Dialog, 대기 등록 번호표
    // extraLarge = RoundedCornerShape()
)
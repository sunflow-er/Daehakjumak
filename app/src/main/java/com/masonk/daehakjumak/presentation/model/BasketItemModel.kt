package com.masonk.daehakjumak.presentation.model

// 장바구니 아이템
data class BasketItemModel(
    val menuId: String, // 메뉴 아이디
    val menuName: String, // 메뉴 이름
    val menuPrice: Int, // 메뉴 가격
    val count: Int // 수량
)

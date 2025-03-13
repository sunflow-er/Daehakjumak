package com.masonk.daehakjumak.domain.model

import com.masonk.daehakjumak.core.MenuType

// 메뉴
data class Menu(
    val id: String, // 메뉴 id
    val type: MenuType, // 메뉴 분류
    var name: String, // 메뉴 이름
    var price: Int, // 메뉴 가격
    var image: String, // 메뉴 이미지
)
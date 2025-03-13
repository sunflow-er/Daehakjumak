package com.masonk.daehakjumak.domain.model

import com.masonk.daehakjumak.core.MenuType

// 메뉴 핵심/필수 정보
data class MenuCore(
    val type: MenuType, // 메뉴 분류
    var name: String, // 메뉴 이름
    var price: Int, // 메뉴 가격
)

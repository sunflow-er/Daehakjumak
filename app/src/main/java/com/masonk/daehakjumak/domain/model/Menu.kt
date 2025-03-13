package com.masonk.daehakjumak.domain.model

// 메뉴
data class Menu(
    val id: String, // 메뉴 id
    val core: MenuCore, // 메뉴 핵심 정보 (분류, 이름, 가격)
    var image: String, // 메뉴 이미지
)
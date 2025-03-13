package com.masonk.daehakjumak.presentation.model

import com.masonk.daehakjumak.core.MenuType
import com.masonk.daehakjumak.domain.model.MenuCore

data class MenuModel(
    val id: String, // 메뉴 id
    val core: MenuCoreModel, // 메뉴 핵심 정보 (분류, 이름, 가격)
    var image: String, // 메뉴 이미지
)

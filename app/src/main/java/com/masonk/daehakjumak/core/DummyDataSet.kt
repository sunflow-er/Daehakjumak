package com.masonk.daehakjumak.core

import com.masonk.daehakjumak.core.enums.MenuType
import com.masonk.daehakjumak.core.enums.OrderStatus
import com.masonk.daehakjumak.domain.model.Order
import com.masonk.daehakjumak.domain.model.Table
import com.masonk.daehakjumak.presentation.model.MenuModel

val dummyTables = listOf(
    Table(
        id = "1",
        number = 1,
        coordinate = Coordinate(100f, 200f),
        orderList = mutableListOf(
            Order("order1", OrderStatus.PREPARING, System.currentTimeMillis(), "table1", MenuType.FOOD, "김치찌개", 8000),
            Order("order2", OrderStatus.COOKING, System.currentTimeMillis(), "table1", MenuType.FOOD, "계란찜", 3000),
            Order("order3", OrderStatus.SERVED, System.currentTimeMillis(), "table1", MenuType.DRINK, "콜라", 2000),
        )
    ),
    Table(
        id = "2",
        number = 2,
        coordinate = Coordinate(300f, 200f),
        orderList = mutableListOf(
            Order("order4", OrderStatus.SERVED, System.currentTimeMillis(), "table2", MenuType.FOOD, "불고기", 12000),
            Order("order5", OrderStatus.SERVED, System.currentTimeMillis(), "table2", MenuType.FOOD, "만두", 4000),
        )
    ),
    Table(
        id = "3",
        number = 3,
        coordinate = Coordinate(500f, 200f),
        orderList = mutableListOf(
            Order("order6", OrderStatus.PREPARING, System.currentTimeMillis(), "table3", MenuType.FOOD, "제육볶음", 8500),
        )
    ),
    Table(
        id = "4",
        number = 4,
        coordinate = Coordinate(100f, 400f),
        orderList = mutableListOf(
            Order("order7", OrderStatus.PREPARING, System.currentTimeMillis(), "table4", MenuType.FOOD, "순두부찌개", 9000),
            Order("order8", OrderStatus.PREPARING, System.currentTimeMillis(), "table4", MenuType.DRINK, "식혜", 2500),
            Order("order9", OrderStatus.PREPARING, System.currentTimeMillis(), "table4", MenuType.FOOD, "김치전", 4500),
            Order("order10", OrderStatus.PREPARING, System.currentTimeMillis(), "table4", MenuType.FOOD, "감자튀김", 4000),
        )
    ),
    Table(
        id = "5",
        number = 5,
        coordinate = Coordinate(300f, 400f),
        orderList = mutableListOf(
            Order("order11", OrderStatus.SERVED, System.currentTimeMillis(), "table5", MenuType.FOOD, "된장찌개", 7000),
            Order("order12", OrderStatus.SERVED, System.currentTimeMillis(), "table5", MenuType.DRINK, "맥주", 5000),
        )
    ),
    Table(
        id = "6",
        number = 6,
        coordinate = Coordinate(500f, 400f),
        orderList = mutableListOf() // 아직 주문 없음
    )
)

val dummyMenuList = listOf(
    MenuModel(
        id = "1",
        type = MenuType.FOOD,
        name = "제육볶음",
        price = 10000,
        image = "image_url_1",
        isSoldOut = false
    ),
    MenuModel(
        id = "2",
        type = MenuType.FOOD,
        name = "김치찌개",
        price = 8000,
        image = "image_url_2",
        isSoldOut = false
    ),
    MenuModel(
        id = "3",
        type = MenuType.DRINK,
        name = "콜라",
        price = 2000,
        image = "image_url_3",
        isSoldOut = true
    ),
    MenuModel(
        id = "4",
        type = MenuType.DRINK,
        name = "사이다",
        price = 2000,
        image = "image_url_4",
        isSoldOut = false
    )
)

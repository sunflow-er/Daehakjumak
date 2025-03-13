package com.masonk.daehakjumak.presentation.mapper

import com.masonk.daehakjumak.domain.model.Jumak
import com.masonk.daehakjumak.domain.model.Menu
import com.masonk.daehakjumak.domain.model.Order
import com.masonk.daehakjumak.domain.model.Table
import com.masonk.daehakjumak.domain.model.WaitingPerson
import com.masonk.daehakjumak.presentation.model.JumakModel
import com.masonk.daehakjumak.presentation.model.MenuModel
import com.masonk.daehakjumak.presentation.model.OrderModel
import com.masonk.daehakjumak.presentation.model.TableModel
import com.masonk.daehakjumak.presentation.model.WaitingPersonModel

// Jumak -> JumakModel
fun Jumak.toPresentation(): JumakModel {
    return JumakModel(
        name = this.name,
        accountNumber = this.accountNumber,
    )
}

// Menu -> MenuModel
fun Menu.toPresentation(): MenuModel {
    return MenuModel(
        id = this.id,
        type = this.type,
        name = this.name,
        price = this.price,
        image = this.image,
    )
}

// Order -> OrderModel
fun Order.toPresentation(): OrderModel {
    return OrderModel(
        id = this.id,
        status = this.status,
        time = this.time,
        tableId = this.tableId,
        menuType = this.menuType,
        menuName = this.menuName,
        menuPrice = this.menuPrice,
    )
}

// Table -> TableModel
fun Table.toPresentation(): TableModel {
    return TableModel(
        id = this.id,
        tableNumber = this.tableNumber,
        isAvailable = this.isAvailable,
        orderList = this.orderList.map { it.toPresentation() }.toMutableList()
    )
}

// WaitingPerson -> WaitingPersonModel
fun WaitingPerson.toPresentation(): WaitingPersonModel {
    return WaitingPersonModel(
        listNumber = this.listNumber,
        WaitingNumber = this.WaitingNumber,
        groupSize = this.groupSize,
        phoneNumber = this.phoneNumber,
        registerAt = this.registerAt
    )
}
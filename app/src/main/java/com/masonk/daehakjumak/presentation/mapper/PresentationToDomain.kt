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

// JumakModel -> Jumak
fun JumakModel.toDomain(): Jumak {
    return Jumak(
        id = this.id,
        name = this.name,
        accountNumber = this.accountNumber,
        isOpen = this.isOpen
    )
}

// MenuModel -> Menu
fun MenuModel.toDomain(): Menu {
    return Menu(
        id = this.id,
        type = this.type,
        name = this.name,
        price = this.price,
        image = this.image,
        isSoldOut = this.isSoldOut
    )
}

// OrderModel -> Order
fun OrderModel.toDomain(): Order {
    return Order(
        id = this.id,
        status = this.status,
        time = this.time,
        tableId = this.tableId,
        menuType = this.menuType,
        menuName = this.menuName,
        menuPrice = this.menuPrice,
    )
}

// TableModel -> Table
fun TableModel.toDomain(): Table {
    return Table(
        id = this.id,
        number = this.number.toInt(),
        coordinate = this.coordinate,
        orderList = this.orderList.map { it.toDomain() }.toMutableList()
    )
}

// WaitingPersonModel -> WaitingPerson
fun WaitingPersonModel.toDomain(): WaitingPerson {
    return WaitingPerson(
        id = this.id,
        listNumber = this.listNumber,
        WaitingNumber = this.WaitingNumber,
        groupSize = this.groupSize,
        phoneNumber = this.phoneNumber,
        registerAt = this.registerAt
    )
}
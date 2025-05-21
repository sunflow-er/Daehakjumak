package com.masonk.daehakjumak.core.util

import java.util.UUID

fun formatPriceIntToStr(price: Int): String {
    return String.format("%,d", price)
}

fun formatPriceStrToInt(price: String): Int {
    return price.replace(",", "").toIntOrNull() ?: 0
}

fun generateRandomId(): String {
    return UUID.randomUUID().toString()
}
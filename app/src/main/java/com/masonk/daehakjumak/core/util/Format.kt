package com.masonk.daehakjumak.core.util

fun formatPriceIntToStr(price: Int): String {
    return String.format("%,d", price)
}

fun formatPriceStrToInt(price: String): Int {
    return price.replace(",", "").toIntOrNull() ?: 0
}
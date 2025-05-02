package com.masonk.daehakjumak.presentation.model.uistate

import com.masonk.daehakjumak.presentation.model.OrderModel

data class JumakSalesInfoUiState(
    val isLoading: Boolean,
    val todaySalesRevenue: Int,
    val todaySalesList: List<OrderModel>,
    val totalSalesRevenue: Int,
    val totalSalesList: List<OrderModel>,
    val error: Throwable?
) {
    companion object {
        val DEFAULT = JumakSalesInfoUiState(
            isLoading = false,
            todaySalesRevenue = 0,
            todaySalesList = emptyList(),
            totalSalesRevenue = 0,
            totalSalesList = emptyList(),
            error = null
        )
    }
}

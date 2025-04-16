package com.masonk.daehakjumak.presentation.model.uistate

import com.masonk.daehakjumak.presentation.model.OrderModel

data class OrderHistoryUiState(
    val isLoading: Boolean,
    val orderHistory: List<OrderModel>,
    val error: Throwable?,
) {
    companion object {
        val DEFAULT = OrderHistoryUiState(
            isLoading = false,
            orderHistory = emptyList(),
            error = null,
        )
    }
}

package com.masonk.daehakjumak.presentation.model.uistate

data class JumakOpenStatusUiState(
    val isLoading: Boolean,
    val isOpen: Boolean,
    val error: Throwable?,
) {
    companion object {
        val DEFAULT = JumakOpenStatusUiState(
            isLoading = false,
            isOpen = false,
            error = null
        )
    }
}

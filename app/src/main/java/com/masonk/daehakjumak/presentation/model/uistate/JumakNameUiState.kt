package com.masonk.daehakjumak.presentation.model.uistate

data class JumakNameUiState(
    val isLoading: Boolean,
    val jumakName: String,
    val error: Throwable?
) {
    companion object {
        val DEFAULT =  JumakNameUiState(
            isLoading = false,
            jumakName = "",
            error = null,
        )
    }
}

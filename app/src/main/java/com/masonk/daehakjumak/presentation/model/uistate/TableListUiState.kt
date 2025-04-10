package com.masonk.daehakjumak.presentation.model.uistate

import com.masonk.daehakjumak.presentation.model.TableModel

data class TableListUiState(
    val isLoading: Boolean,
    val tableList: List<TableModel>,
    val error: Throwable?,
) {
    companion object {
        val DEFAULT =  TableListUiState(
            isLoading = false,
            tableList = emptyList(),
            error = null,
        )
    }
}

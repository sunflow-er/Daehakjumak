package com.masonk.daehakjumak.presentation.model.uistate

import com.masonk.daehakjumak.presentation.model.MenuModel

data class MenuBoardUiState(
    val isLoading: Boolean,
    val menuList: List<MenuModel>,
    val error: Throwable?,
) {
    companion object {
        val DEFAULT = MenuBoardUiState(
            isLoading = false,
            menuList = emptyList(),
            error = null,
        )
    }
}

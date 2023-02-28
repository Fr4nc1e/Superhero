package com.comic.superhero.core.presentation.ui.util

sealed class CoreUiEvent {
    data class ShowSnackbar(val uiText: UiText) : CoreUiEvent()
}

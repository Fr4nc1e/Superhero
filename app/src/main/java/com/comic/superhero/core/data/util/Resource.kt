package com.comic.superhero.core.data.util

import com.comic.superhero.core.presentation.ui.util.UiText

sealed class Resource<T>(
    val data: T? = null,
    val uiText: UiText? = null
) {
    class Success<T>(data: T? = null, uiText: UiText? = null) : Resource<T>(data, uiText)
    class Error<T>(data: T? = null, uiText: UiText? = null) : Resource<T>(data, uiText)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}

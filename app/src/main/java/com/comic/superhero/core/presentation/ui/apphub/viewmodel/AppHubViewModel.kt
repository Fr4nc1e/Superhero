package com.comic.superhero.core.presentation.ui.apphub.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class AppHubViewModel @Inject constructor() : ViewModel() {
    private val _curRoute = MutableStateFlow("")
    val curRoute = _curRoute.asStateFlow()

    fun getCurRoute(route: String) {
        _curRoute.update { route }
    }
}

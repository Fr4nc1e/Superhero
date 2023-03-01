package com.comic.superhero.feature.detail.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.superhero.core.data.util.Resource
import com.comic.superhero.core.presentation.ui.util.CoreUiEvent
import com.comic.superhero.feature.home.domain.model.SuperHero
import com.comic.superhero.feature.home.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _superHero = MutableStateFlow(SuperHero())
    val superHero = _superHero.asStateFlow()

    private val _eventFlow = MutableSharedFlow<CoreUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    init {
        savedStateHandle.get<String>("heroId")?.let {
            getSuperHero(it)
        }
    }

    private fun getSuperHero(heroId: String) {
        viewModelScope.launch {
            homeUseCase.getSuperHero(heroId).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        result.uiText?.let { uiText ->
                            _eventFlow.emit(CoreUiEvent.ShowSnackbar(uiText))
                        }
                    }
                    is Resource.Loading -> { _loadingState.value = result.isLoading }
                    is Resource.Success -> {
                        result.data?.let { hero ->
                            _superHero.update { hero }
                        }
                    }
                }
            }
        }
    }
}

package com.comic.superhero.feature.home.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.superhero.R
import com.comic.superhero.core.data.util.Resource
import com.comic.superhero.core.presentation.ui.util.CoreUiEvent
import com.comic.superhero.core.presentation.ui.util.UiText
import com.comic.superhero.feature.home.domain.model.SuperHero
import com.comic.superhero.feature.home.presentation.event.HomeEvent
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
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _superHeroList = MutableStateFlow<List<SuperHero>>(emptyList())

    private val _superHero = MutableStateFlow(SuperHero())
    val superHero = _superHero.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<CoreUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var idBufferList = mutableStateOf<List<Int>>(emptyList())

    private val _heroId = mutableStateOf(0)

    init {
        getSuperHero()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.GetSuperHero -> {
                if (_superHeroList.value.size <= 5) {
                    getSuperHero()
                }
            }

            HomeEvent.NextHero -> {
                if (_superHeroList.value.isNotEmpty() && idBufferList.value.size < 731) {
                    _superHeroList.update { it.drop(1) }
                    _superHero.update { _superHeroList.value.first() }
                }
                if (idBufferList.value.size == 731) {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            CoreUiEvent.ShowSnackbar(
                                uiText = UiText.StringResource(R.string.no_more_heroes)
                            )
                        )
                    }
                }
            }
        }
    }

    private fun getSuperHero() {
        viewModelScope.launch {
            repeat(10) {
                while (true) {
                    _heroId.value = (1..731).random()
                    if (_heroId.value !in idBufferList.value) {
                        idBufferList.value.plus(it)
                        break
                    }
                }
                homeUseCase.getSuperHero(_heroId.value.toString()).collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            result.uiText?.let { uiText ->
                                _eventFlow.emit(CoreUiEvent.ShowSnackbar(uiText))
                            }
                        }
                        is Resource.Loading -> {
                            _loadingState.value = result.isLoading
                        }
                        is Resource.Success -> {
                            result.data?.let { hero ->
                                if (_superHero.value == SuperHero()) {
                                    _superHero.update { hero }
                                }
                                _superHeroList.update { list ->
                                    list + hero
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

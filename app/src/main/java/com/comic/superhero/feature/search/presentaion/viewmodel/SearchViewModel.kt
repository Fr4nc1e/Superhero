package com.comic.superhero.feature.search.presentaion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.superhero.R
import com.comic.superhero.core.data.util.Resource
import com.comic.superhero.core.presentation.ui.naviagtion.destination.Destination
import com.comic.superhero.core.presentation.ui.util.CoreUiEvent
import com.comic.superhero.core.presentation.ui.util.UiText
import com.comic.superhero.feature.search.domain.model.Item
import com.comic.superhero.feature.search.presentaion.event.SearchEvent
import com.comic.superhero.feature.search.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    private val _inputText = MutableStateFlow("")
    val inputText = _inputText.asStateFlow()

    private val _superHeroList = MutableStateFlow<List<Item?>>(emptyList())
    val superHeroList = _superHeroList.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    private val _showKeyBoardState = MutableSharedFlow<Boolean>()
    val showKeyBoardState = _showKeyBoardState.asSharedFlow()

    private val _eventFlow = MutableSharedFlow<CoreUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _showKeyBoardState.emit(true)
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.EnterDetail -> {
                viewModelScope.launch {
                    _eventFlow.emit(
                        CoreUiEvent.Navigate(Destination.DetailScreen.route + "/${event.id}")
                    )
                }
            }
            is SearchEvent.SearchSuperHero -> {
                searchHero(event.name)
            }
            is SearchEvent.InputName -> {
                _inputText.update { event.text }
            }
        }
    }

    private fun searchHero(name: String) {
        viewModelScope.launch {
            when {
                name.isBlank() -> {
                    _eventFlow.emit(
                        CoreUiEvent.ShowSnackbar(UiText.StringResource(R.string.empty_input))
                    )
                }
                else -> {
                    searchUseCase(name).collect { result ->
                        when (result) {
                            is Resource.Error -> {
                                result.uiText?.let { uiText ->
                                    _eventFlow.emit(CoreUiEvent.ShowSnackbar(uiText))
                                }
                            }

                            is Resource.Loading -> { _loadingState.value = result.isLoading }
                            is Resource.Success -> {
                                result.data?.let { list ->
                                    _superHeroList.update { list }
                                } ?: _eventFlow.emit(
                                    CoreUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.no_result)
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

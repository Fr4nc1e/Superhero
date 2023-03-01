package com.comic.superhero.feature.search.presentaion.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comic.superhero.R
import com.comic.superhero.core.presentation.ui.util.CoreUiEvent
import com.comic.superhero.core.presentation.ui.util.asString
import com.comic.superhero.feature.search.presentaion.event.SearchEvent
import com.comic.superhero.feature.search.presentaion.ui.component.HeroItem
import com.comic.superhero.feature.search.presentaion.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    onNavigate: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val inputText = viewModel.inputText.collectAsState().value
    val heroes = viewModel.superHeroList.collectAsState().value
    val isLoading = viewModel.loadingState.collectAsState().value
    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0

    LaunchedEffect(viewModel.showKeyBoardState) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is CoreUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                is CoreUiEvent.Navigate -> {
                    onNavigate(event.route)
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            state = lazyListState
        ) {
            item {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = {
                        viewModel.onEvent(SearchEvent.InputName(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .padding(8.dp)
                        .graphicsLayer {
                            scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                            translationY = scrolledY * 0.5f
                            previousOffset = lazyListState.firstVisibleItemScrollOffset
                        },
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.onEvent(SearchEvent.SearchSuperHero(inputText))
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(id = R.string.search)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        viewModel.onEvent(SearchEvent.SearchSuperHero(inputText))
                    }),
                    shape = RoundedCornerShape(16.dp)
                )
            }
            items(heroes) {
                it?.let {
                    HeroItem(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                it.id?.let { id ->
                                    viewModel.onEvent(
                                        SearchEvent.EnterDetail(id)
                                    )
                                }
                            },
                        item = it
                    )
                }
            }
        }
    }
}

package com.comic.superhero.feature.home.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comic.superhero.core.presentation.ui.util.CoreUiEvent
import com.comic.superhero.core.presentation.ui.util.ImageLoader
import com.comic.superhero.core.presentation.ui.util.asString
import com.comic.superhero.feature.home.presentation.event.HomeEvent
import com.comic.superhero.feature.home.presentation.ui.component.BasicInfo
import com.comic.superhero.feature.home.presentation.ui.component.PowerStatsInfo
import com.comic.superhero.feature.home.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val superHero = viewModel.superHero.collectAsState().value
    val isLoading = viewModel.loadingState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is CoreUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Center))
        }

        LazyColumn(Modifier.fillMaxSize()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(390.dp)
                        .clip(RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
                        .combinedClickable(
                            onClick = {},
                            onDoubleClick = {
                                viewModel.apply {
                                    onEvent(HomeEvent.NextHero)
                                    onEvent(HomeEvent.GetSuperHero)
                                }
                            }
                        )
                ) {
                    ImageLoader(
                        modifier = Modifier.fillMaxSize().aspectRatio(1f),
                        url = superHero.imageUrl
                    )
                }
            }

            item {
                BasicInfo(
                    superHero = superHero,
                    modifier = Modifier.padding(16.dp)
                )
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(CenterVertically)
                        .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                ) {
                    superHero.powerstats?.let {
                        PowerStatsInfo(
                            modifier = Modifier.padding(
                                top = 32.dp,
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 32.dp
                            ),
                            heroStats = it
                        )
                    }
                }
            }
        }
    }
}

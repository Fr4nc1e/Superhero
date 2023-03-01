package com.comic.superhero.feature.detail.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comic.superhero.core.presentation.ui.util.CoreUiEvent
import com.comic.superhero.core.presentation.ui.util.ImageLoader
import com.comic.superhero.core.presentation.ui.util.asString
import com.comic.superhero.feature.detail.presentation.viewmodel.DetailViewModel
import com.comic.superhero.feature.home.presentation.ui.component.BasicInfo
import com.comic.superhero.feature.home.presentation.ui.component.PowerStatsInfo

@Composable
fun DetailScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val superHero = viewModel.superHero.collectAsState().value
    val isLoading = viewModel.loadingState.collectAsState().value
    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is CoreUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                else -> {}
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            item {
                Column(
                    modifier.graphicsLayer {
                        scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                        translationY = scrolledY * 0.5f
                        previousOffset = lazyListState.firstVisibleItemScrollOffset
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(390.dp)
                            .clip(RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
                    ) {
                        ImageLoader(
                            modifier = Modifier
                                .fillMaxSize()
                                .aspectRatio(1f),
                            url = superHero.imageUrl
                        )
                    }

                    BasicInfo(
                        superHero = superHero,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(360.dp))
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .clip(RoundedCornerShape(45.dp))
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
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

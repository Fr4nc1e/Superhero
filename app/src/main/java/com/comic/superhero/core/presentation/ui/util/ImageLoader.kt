package com.comic.superhero.core.presentation.ui.util

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.comic.superhero.R

@Composable
fun ImageLoader(
    modifier: Modifier,
    url: String?
) {
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = url)
                .apply(
                    block = fun ImageRequest.Builder.() {
                        crossfade(true)
                    }
                ).build()
        ),
        contentDescription = stringResource(R.string.hero_image),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

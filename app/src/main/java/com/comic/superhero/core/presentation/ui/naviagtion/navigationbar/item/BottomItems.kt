package com.comic.superhero.core.presentation.ui.naviagtion.navigationbar.item

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.comic.superhero.R
import com.comic.superhero.core.presentation.ui.naviagtion.destination.Destination

enum class BottomItems(
    val route: String,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    Home(
        route = Destination.HomeScreen.route,
        icon = Icons.Filled.Home,
        contentDescription = R.string.home
    ),
    Search(
        route = Destination.SearchScreen.route,
        icon = Icons.Filled.Search,
        contentDescription = R.string.search
    ),
    Favorite(
        route = Destination.FavoriteScreen.route,
        icon = Icons.Filled.Favorite,
        contentDescription = R.string.favorite
    )
}

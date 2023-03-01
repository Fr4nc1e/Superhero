package com.comic.superhero.core.presentation.ui.naviagtion.destination

sealed class Destination(val route: String) {
    object HomeScreen : Destination("home")
    object SearchScreen : Destination("search")
    object DetailScreen : Destination("detail")
}

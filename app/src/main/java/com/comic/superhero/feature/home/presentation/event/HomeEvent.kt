package com.comic.superhero.feature.home.presentation.event

sealed class HomeEvent {
    object GetSuperHero : HomeEvent()
    object NextHero : HomeEvent()
}

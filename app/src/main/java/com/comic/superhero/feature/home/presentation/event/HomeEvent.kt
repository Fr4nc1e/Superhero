package com.comic.superhero.feature.home.presentation.event

sealed class HomeEvent {
    data class GetSuperHero(val heroId: String) : HomeEvent()
}

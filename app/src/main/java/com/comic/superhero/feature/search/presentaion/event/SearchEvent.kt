package com.comic.superhero.feature.search.presentaion.event

sealed class SearchEvent {
    data class InputName(val text: String) : SearchEvent()
    data class SearchSuperHero(val name: String) : SearchEvent()
    data class EnterDetail(val id: String) : SearchEvent()
}

package com.comic.superhero.feature.home.domain.model

data class SuperHero(
    var id: String? = null,
    var name: String? = null,
    var imageUrl: String? = null,
    val powerstats: Powerstats? = null,
    val biography: Biography? = null,
    val appearance: Appearance? = null
)

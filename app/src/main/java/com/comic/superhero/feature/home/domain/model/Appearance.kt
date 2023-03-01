package com.comic.superhero.feature.home.domain.model

data class Appearance(
    val eyeColor: String?,
    val gender: String?,
    val hairColor: String?,
    val height: List<String?>?,
    val race: String?,
    val weight: List<String?>?
)

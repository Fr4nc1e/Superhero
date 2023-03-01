package com.comic.superhero.feature.home.data.dto

import com.comic.superhero.feature.home.domain.model.Appearance
import com.google.gson.annotations.SerializedName

data class AppearanceDto(
    @SerializedName("eye-color")
    val eyeColor: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("hair-color")
    val hairColor: String?,
    @SerializedName("height")
    val height: List<String?>?,
    @SerializedName("race")
    val race: String?,
    @SerializedName("weight")
    val weight: List<String?>?
) {
    fun toAppearance() = Appearance(
        eyeColor = eyeColor,
        gender = gender,
        hairColor = hairColor,
        height = height,
        race = race,
        weight = weight
    )
}

package com.comic.superhero.feature.home.data.dto

import com.comic.superhero.feature.home.domain.model.Biography
import com.google.gson.annotations.SerializedName

data class BiographyDto(
    @SerializedName("aliases")
    val aliases: List<String?>?,
    @SerializedName("alignment")
    val alignment: String?,
    @SerializedName("alter-egos")
    val alterEgos: String?,
    @SerializedName("first-appearance")
    val firstAppearance: String?,
    @SerializedName("full-name")
    val fullName: String?,
    @SerializedName("place-of-birth")
    val placeOfBirth: String?,
    @SerializedName("publisher")
    val publisher: String?
) {
    fun toBiography() = Biography(
        firstAppearance = firstAppearance,
        fullName = fullName,
        placeOfBirth = placeOfBirth,
        publisher = publisher
    )
}

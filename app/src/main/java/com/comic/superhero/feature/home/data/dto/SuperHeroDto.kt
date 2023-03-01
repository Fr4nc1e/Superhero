package com.comic.superhero.feature.home.data.dto

import com.comic.superhero.feature.home.domain.model.SuperHero
import com.google.gson.annotations.SerializedName

data class SuperHeroDto(
    @SerializedName("appearance")
    val appearanceDto: AppearanceDto?,
    @SerializedName("biography")
    val biographyDto: BiographyDto?,
    @SerializedName("connections")
    val connectionsDto: ConnectionsDto?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val imageDto: ImageDto?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("powerstats")
    val powerstatsDto: PowerstatsDto?,
    @SerializedName("response")
    val response: String?,
    @SerializedName("work")
    val workDto: WorkDto?
) {
    fun toSuperHero() = SuperHero(
        id = id,
        name = name,
        imageUrl = imageDto?.toImage()?.url,
        powerstats = powerstatsDto?.toPowerStats(),
        biography = biographyDto?.toBiography(),
        appearance = appearanceDto?.toAppearance()
    )
}

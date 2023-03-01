package com.comic.superhero.feature.search.data.dto

import com.comic.superhero.feature.search.domain.model.Item
import com.google.gson.annotations.SerializedName

data class ResultDto(
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
    @SerializedName("work")
    val workDto: WorkDto?
) {
    fun toItem() = Item(
        id = id,
        image = imageDto?.toImage()?.url,
        name = name
    )
}

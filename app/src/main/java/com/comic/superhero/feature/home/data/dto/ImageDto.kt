package com.comic.superhero.feature.home.data.dto

import com.comic.superhero.feature.home.domain.model.Image
import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("url")
    val url: String?
) {
    fun toImage() = Image(url)
}

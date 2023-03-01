package com.comic.superhero.feature.search.data.dto

import com.comic.superhero.feature.search.domain.model.Image
import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("url")
    val url: String?
) {
    fun toImage() = Image(url)
}

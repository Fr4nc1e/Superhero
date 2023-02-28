package com.comic.superhero.feature.home.data.dto

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url")
    val url: String?
)

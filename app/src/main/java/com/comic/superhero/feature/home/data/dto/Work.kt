package com.comic.superhero.feature.home.data.dto

import com.google.gson.annotations.SerializedName

data class Work(
    @SerializedName("base")
    val base: String?,
    @SerializedName("occupation")
    val occupation: String?
)

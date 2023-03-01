package com.comic.superhero.feature.search.data.dto

import com.google.gson.annotations.SerializedName

data class WorkDto(
    @SerializedName("base")
    val base: String?,
    @SerializedName("occupation")
    val occupation: String?
)

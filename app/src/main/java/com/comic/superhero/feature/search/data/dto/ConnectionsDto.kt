package com.comic.superhero.feature.search.data.dto

import com.google.gson.annotations.SerializedName

data class ConnectionsDto(
    @SerializedName("group-affiliation")
    val groupAffiliation: String?,
    @SerializedName("relatives")
    val relatives: String?
)

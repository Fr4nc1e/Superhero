package com.comic.superhero.feature.search.data.dto

import com.comic.superhero.feature.search.domain.model.SearchedHeroes
import com.google.gson.annotations.SerializedName

data class SearchedHeroesDto(
    @SerializedName("response")
    val response: String?,
    @SerializedName("results")
    val resultsDto: List<ResultDto?>?,
    @SerializedName("results-for")
    val resultsFor: String?
) {
    fun toSearchedHeroes() = SearchedHeroes(resultsDto?.map { it?.toItem() })
}

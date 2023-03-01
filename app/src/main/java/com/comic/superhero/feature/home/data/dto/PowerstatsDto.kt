package com.comic.superhero.feature.home.data.dto

import com.comic.superhero.feature.home.domain.model.Powerstats
import com.google.gson.annotations.SerializedName

data class PowerstatsDto(
    @SerializedName("combat")
    val combat: String?,
    @SerializedName("durability")
    val durability: String?,
    @SerializedName("intelligence")
    val intelligence: String?,
    @SerializedName("power")
    val power: String?,
    @SerializedName("speed")
    val speed: String?,
    @SerializedName("strength")
    val strength: String?
) {
    fun toPowerStats() = Powerstats(
        combat = if (combat != "null") combat?.toInt() else 0,
        durability = if (durability != "null") durability?.toInt() else 0,
        intelligence = if (intelligence != "null") intelligence?.toInt() else 0,
        power = if (power != "null") power?.toInt() else 0,
        speed = if (speed != "null") speed?.toInt() else 0,
        strength = if (strength != "null") strength?.toInt() else 0
    )
}

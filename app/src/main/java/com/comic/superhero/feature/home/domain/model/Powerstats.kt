package com.comic.superhero.feature.home.domain.model

import java.util.HashMap

data class Powerstats(
    val combat: Int?,
    val durability: Int?,
    val intelligence: Int?,
    val power: Int?,
    val speed: Int?,
    val strength: Int?
) {
    fun toMap(): Map<String, Int> {
        val map = HashMap<String, Int>()
        combat?.let {
            map["combat"] = it
        }
        durability?.let {
            map["durability"] = it
        }
        intelligence?.let {
            map["intelligence"] = it
        }
        power?.let {
            map["power"] = it
        }
        speed?.let {
            map["speed"] = it
        }
        strength?.let {
            map["strength"] = it
        }

        return map
    }
}

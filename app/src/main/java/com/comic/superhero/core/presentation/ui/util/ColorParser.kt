package com.comic.superhero.core.presentation.ui.util

import androidx.compose.ui.graphics.Color

object ColorParser {
    fun getColor(stat: String): Color {
        return when (stat) {
            "combat" -> Color(0xFFF5FF00)
            "durability" -> Color(0f, 0f, 1f, 0.44f)
            "intelligence" -> Color(0f, 1f, 0.063f, 0.55f)
            "power" -> Color(1f, 0f, 0f, 0.66f)
            "speed" -> Color(0.671f, 0f, 1f, 0.57f)
            "strength" -> Color(1f, 0f, 0.8f, 0.7f)
            else -> Color.White
        }
    }
}

package com.comic.superhero.feature.home.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.comic.superhero.core.presentation.ui.util.ColorParser
import com.comic.superhero.feature.home.domain.model.Powerstats

@Composable
fun PowerStatsInfo(
    modifier: Modifier,
    heroStats: Powerstats,
    animDelayPerItem: Int = 100
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "PowerStats",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
        )
        heroStats.toMap().forEach {
            HeroStat(
                statName = it.key,
                statValue = it.value,
                statMaxValue = 100,
                statColor = ColorParser.getColor(it.key),
                animDelay = animDelayPerItem
            )
        }
    }
}

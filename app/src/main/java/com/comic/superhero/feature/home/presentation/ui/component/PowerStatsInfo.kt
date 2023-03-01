package com.comic.superhero.feature.home.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        modifier = modifier.fillMaxWidth()
    ) {
        heroStats.toMap().forEach {
            HeroStat(
                statName = it.key,
                statValue = it.value,
                statMaxValue = 100,
                statColor = ColorParser.getColor(it.key),
                animDelay = animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

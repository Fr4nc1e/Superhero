package com.comic.superhero.feature.home.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.comic.superhero.R
import com.comic.superhero.feature.home.domain.model.SuperHero

@Composable
fun BasicInfo(
    superHero: SuperHero,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            superHero.name?.let {
                if (it.isNotBlank()) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            IconToggleButton(
                checked = true,
                onCheckedChange = {},
                modifier = modifier.padding(end = 8.dp, top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = stringResource(id = R.string.favorite)
                )
            }
        }

        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            superHero.biography?.fullName?.let {
                if (it != "-" && it.isNotBlank()) {
                    Text(
                        text = "Full Name: $it",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            superHero.biography?.placeOfBirth?.let {
                if (it != "-") {
                    Text(
                        text = "Birth Place: $it",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            superHero.biography?.firstAppearance?.let {
                if (it != "-") {
                    Text(
                        text = "First Appearance: $it",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            superHero.biography?.publisher?.let {
                if (it != "-") {
                    Text(
                        text = "Publisher: $it",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

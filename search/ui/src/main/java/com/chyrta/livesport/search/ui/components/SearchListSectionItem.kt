package com.chyrta.livesport.search.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chyrta.livesport.search.logic.domain.model.SportEntity
import com.chyrta.livesport.common.R as CommonR
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun SearchListSectionItem(sport: SportEntity) {
    val sportTitleResId = when (sport) {
        SportEntity.Football -> CommonR.string.sport_football
        SportEntity.Tennis -> CommonR.string.sport_tennis
        SportEntity.Basketball -> CommonR.string.sport_basketball
        SportEntity.Hockey -> CommonR.string.sport_hockey
        SportEntity.AmericanFootball -> CommonR.string.sport_american_football
        SportEntity.Baseball -> CommonR.string.sport_baseball
        SportEntity.Handball -> CommonR.string.sport_handball
        SportEntity.Rugby -> CommonR.string.sport_rugby
        SportEntity.Floorball -> CommonR.string.sport_floorball
    }

    Text(
        text = stringResource(id = sportTitleResId),
        style = OrbitTheme.typography.title4,
        modifier = Modifier.padding(vertical = 6.dp),
    )
}

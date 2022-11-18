package com.chyrta.livesport.search.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chyrta.livesport.search.logic.domain.model.SportEntity
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun SearchListSectionItem(sport: SportEntity) {
    Text(
        text = sport.name,
        style = OrbitTheme.typography.title4,
        modifier = Modifier.padding(vertical = 6.dp),
    )
}

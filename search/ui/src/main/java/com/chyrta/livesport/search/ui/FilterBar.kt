package com.chyrta.livesport.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.presentation.SearchContract
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Tag
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun FilterBar(
    selectedFilter: SearchFilter,
    modifier: Modifier = Modifier,
    onSelectAll: () -> Unit,
    onSelectParticipants: () -> Unit,
    onSelectCompetitions: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Tag(
            selected = selectedFilter == SearchFilter.All,
            onSelect = onSelectAll,
        ) {
            Text("All")
        }
        Tag(
            selected = selectedFilter == SearchFilter.Competitions,
            onSelect = onSelectCompetitions,
        ) {
            Text("Competitions")
        }
        Tag(
            selected = selectedFilter == SearchFilter.Participants,
            onSelect = onSelectParticipants,
        ) {
            Text("Participants")
        }
    }
}

@Preview
@Composable
fun FilterBarPreview() {
    OrbitTheme {
        Scaffold {
            FilterBar(
                selectedFilter = SearchFilter.All,
                onSelectAll = {},
                onSelectParticipants = {},
                onSelectCompetitions = {},
            )
        }
    }
}

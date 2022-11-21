package com.chyrta.livesport.search.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Tag
import kiwi.orbit.compose.ui.controls.Text
import com.chyrta.livesport.common.R as CommonR

@Composable
fun FilterBar(
    selectedFilter: SearchFilter,
    modifier: Modifier = Modifier,
    onSelectFilter: (SearchFilter) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SearchFilter.values().map {
            val isSelectedTag = selectedFilter == it
            val tagTitle = stringResource(when (it) {
                SearchFilter.All -> CommonR.string.all
                SearchFilter.Competitions -> CommonR.string.competitions
                SearchFilter.Participants -> CommonR.string.participants
            })
            Tag(
                selected = isSelectedTag,
                onSelect = { onSelectFilter(it) },
            ) {
                Text(tagTitle)
            }
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
                onSelectFilter = {}
            )
        }
    }
}

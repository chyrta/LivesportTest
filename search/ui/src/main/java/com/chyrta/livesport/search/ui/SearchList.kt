package com.chyrta.livesport.search.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.search.logic.domain.model.SportEntity
import com.chyrta.livesport.search.logic.domain.model.SportResultItemEntity
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun SearchList(
    modifier: Modifier = Modifier,
    state: LazyListState,
    onItemClick: (SearchResultItemEntity) -> Unit,
    resultItems: Map<SportEntity, List<SearchResultItemEntity>>
) {
    LazyColumn(
        modifier = modifier,
        state = state,
    ) {
        resultItems.map {
            item {
                SearchListSectionItem(sport = it.key)
            }
            items(it.value) { resultItem ->
                SearchListItem(
                    item = resultItem,
                    onItemClick = onItemClick
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

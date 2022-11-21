package com.chyrta.livesport.search.logic.presentation.model

import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.search.logic.domain.model.SportEntity

data class SearchResultViewItem(
    val sport: SportEntity,
    val results: List<SearchResultItemEntity>
)

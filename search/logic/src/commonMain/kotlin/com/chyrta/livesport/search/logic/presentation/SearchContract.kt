package com.chyrta.livesport.search.logic.presentation

import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.search.logic.domain.model.SportEntity
import com.chyrta.livesport.common.base.mvi.UiEffect
import com.chyrta.livesport.common.base.mvi.UiEvent
import com.chyrta.livesport.common.base.mvi.UiState

interface SearchContract {
    sealed interface Event: UiEvent {
        data class OnSearchQuery(val text: String): Event
        object OnClearQuery: Event
        object OnSearch: Event
        object OnSelectAll: Event
        object OnSelectCompetitions: Event
        object OnSelectParticipants: Event
    }

    data class State(
        val query: String = "",
        val selectedFilter: SearchFilter = SearchFilter.All,
        val items: Map<SportEntity, List<SearchResultItemEntity>> = emptyMap(),
        val isLoading: Boolean = false
    ): UiState

    sealed interface Effect: UiEffect {
        object HideKeyboard: Effect
        object ScrollResultToTop: Effect
    }

}

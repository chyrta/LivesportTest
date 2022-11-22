package com.chyrta.livesport.search.logic.presentation

import com.chyrta.livesport.common.base.mvi.UiEvent
import com.chyrta.livesport.common.base.mvi.UiState
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity

interface SearchDetailContract {
    sealed interface Event : UiEvent {
        data class GetResultItem(val resultItem: SearchResultItemEntity) : Event
    }

    data class State(
        val item: SearchResultItemEntity? = null,
    ) : UiState
}

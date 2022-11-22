package com.chyrta.livesport.search.logic.presentation

import com.chyrta.livesport.common.base.mvi.UiEffect
import com.chyrta.livesport.common.base.mvi.UiEvent
import com.chyrta.livesport.common.base.mvi.UiState
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.presentation.model.SearchResultViewItem

interface SearchContract {
    sealed interface Event : UiEvent {
        data class OnSearchQuery(val text: String) : Event
        object OnClearQuery : Event
        object OnSearch : Event
        data class OnSelectFilter(val searchFilter: SearchFilter) : Event
    }

    enum class SearchErrorState {
        InvalidRequestParameters,
        MissingRequestParameters,
        NetworkError,
        HttpError,
        ServiceUnavailable,
        UnknownError
    }

    data class State(
        val query: String,
        val selectedFilter: SearchFilter,
        val items: List<SearchResultViewItem>,
        val isLoading: Boolean,
        val isSearchCompleted: Boolean,
        val errorState: SearchErrorState? = null
    ) : UiState {

        val emptyState = isLoading.not() && isSearchCompleted.not() && items.isEmpty()
        val isLoadingResults = isLoading && isSearchCompleted.not()
        val hasResults = !isLoadingResults && items.isNotEmpty()
        val hasNoResults = !isLoadingResults && items.isEmpty()
        val hasError = !isLoadingResults && errorState != null

        companion object {
            fun idle(): State = State(
                query = "",
                selectedFilter = SearchFilter.All,
                items = emptyList(),
                isLoading = false,
                isSearchCompleted = false
            )
        }
    }

    sealed interface Effect : UiEffect {
        object HideKeyboard : Effect
        object ScrollResultToTop : Effect
    }
}

package com.chyrta.livesport.search.logic.presentation

import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.domain.usecase.GetSearchResultUseCase
import com.chyrta.livesport.common.base.mvi.BaseViewModel
import kotlinx.coroutines.launch
import com.chyrta.livesport.search.logic.presentation.SearchContract.Event
import com.chyrta.livesport.search.logic.presentation.SearchContract.State
import com.chyrta.livesport.search.logic.presentation.SearchContract.Effect

class SearchViewModel(
    private val getSearchResultUseCase: GetSearchResultUseCase,
) : BaseViewModel<Event, State, Effect>() {

    override fun createInitialState(): State = State()

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnSearch -> {
                search()
                setEffect { Effect.HideKeyboard }
            }
            is Event.OnSearchQuery -> {
                setState { copy(query = event.text) }
            }
            is Event.OnClearQuery -> {
                setState { copy(query = "", items = emptyMap()) }
                setEffect { Effect.HideKeyboard }
            }
            is Event.OnSelectAll -> setState { copy(selectedFilter = SearchFilter.All) }
            is Event.OnSelectCompetitions -> setState { copy(selectedFilter = SearchFilter.Competitions) }
            is Event.OnSelectParticipants -> setState { copy(selectedFilter = SearchFilter.Participants) }
        }
    }

    private fun search() = launch {
        setState { copy(isLoading = true) }
        val result = getSearchResultUseCase(currentState.query, currentState.selectedFilter)
        if (result.isSuccess) {
            val items = result.getOrNull().orEmpty()
            setState { copy(items = items.groupBy { it.sport }, isLoading = false) }
            setEffect { Effect.ScrollResultToTop }
        } else {
            setState { copy(isLoading = false) }
        }
    }

}

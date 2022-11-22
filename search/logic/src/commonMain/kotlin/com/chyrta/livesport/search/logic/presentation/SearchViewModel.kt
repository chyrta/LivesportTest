package com.chyrta.livesport.search.logic.presentation

import com.chyrta.livesport.common.base.mvi.BaseViewModel
import com.chyrta.livesport.common.util.Either
import com.chyrta.livesport.search.logic.domain.model.GetSearchResultError
import com.chyrta.livesport.search.logic.domain.usecase.GetSearchResultUseCase
import com.chyrta.livesport.search.logic.presentation.SearchContract.Effect
import com.chyrta.livesport.search.logic.presentation.SearchContract.Event
import com.chyrta.livesport.search.logic.presentation.SearchContract.SearchErrorState
import com.chyrta.livesport.search.logic.presentation.SearchContract.State
import com.chyrta.livesport.search.logic.presentation.model.SearchResultViewItem
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class SearchViewModel : BaseViewModel<Event, State, Effect>(), KoinComponent {

    private val getSearchResultUseCase: GetSearchResultUseCase by inject()

    override fun createInitialState(): State = State.idle()

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnSearch -> {
                setEffect { Effect.HideKeyboard }
                search()
            }
            is Event.OnSearchQuery -> {
                setState { copy(query = event.text) }
            }
            is Event.OnClearQuery -> {
                setState {
                    copy(
                        query = "",
                        items = emptyList(),
                        errorState = null,
                        isLoading = false,
                        isSearchCompleted = false
                    )
                }
                setEffect { Effect.HideKeyboard }
            }
            is Event.OnSelectFilter -> setState { copy(selectedFilter = event.searchFilter) }
        }
    }

    private fun search() = launch {
        setState {
            copy(
                isLoading = true,
                isSearchCompleted = false,
                errorState = null
            )
        }
        val result = getSearchResultUseCase(currentState.query, currentState.selectedFilter)
        when (result) {
            is Either.Left -> {
                val items = result
                    .value
                    .map { SearchResultViewItem(it.key, it.value) }

                setState {
                    copy(
                        items = items,
                        isLoading = false,
                        isSearchCompleted = true
                    )
                }
                setEffect { Effect.ScrollResultToTop }
            }
            is Either.Right -> {
                val errorState = when (result.value) {
                    is GetSearchResultError.HttpError -> SearchErrorState.HttpError
                    is GetSearchResultError.InvalidRequestParameters -> SearchErrorState.InvalidRequestParameters
                    is GetSearchResultError.MissingRequestParameters -> SearchErrorState.MissingRequestParameters
                    is GetSearchResultError.NetworkError -> SearchErrorState.NetworkError
                    is GetSearchResultError.ServiceUnavailable -> SearchErrorState.ServiceUnavailable
                    is GetSearchResultError.UnknownError -> SearchErrorState.UnknownError
                }
                setState {
                    copy(
                        isLoading = false,
                        isSearchCompleted = true,
                        errorState = errorState
                    )
                }
            }
        }
    }
}

package com.chyrta.livesport.search.logic.presentation

import com.chyrta.livesport.common.base.mvi.BaseViewModel

class SearchDetailViewModel :
    BaseViewModel<SearchDetailContract.Event, SearchDetailContract.State, Nothing>() {

    override fun createInitialState(): SearchDetailContract.State = SearchDetailContract.State()

    override fun handleEvent(event: SearchDetailContract.Event) {
        when (event) {
            is SearchDetailContract.Event.GetResultItem -> setState { copy(item = event.resultItem) }
        }
    }

}

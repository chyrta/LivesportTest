package com.chyrta.livesport.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.search.logic.presentation.SearchContract
import com.chyrta.livesport.search.logic.presentation.SearchViewModel
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TopAppBar
import kiwi.orbit.compose.ui.utils.plus
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onNavigateToDetail: (SearchResultItemEntity) -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchListState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Search") })
        },
    ) { contentPadding ->
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .padding(contentPadding + PaddingValues(16.dp)),
        ) {
            val (searchBar, filterBar, searchList) = createRefs()

            SearchBar(
                modifier = Modifier.constrainAs(searchBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                searchQuery = state.query,
                onClearQueryClick = { viewModel.setEvent(SearchContract.Event.OnClearQuery) },
                onChangeQuery = { viewModel.setEvent(SearchContract.Event.OnSearchQuery(it)) },
                onSearchClick = { viewModel.setEvent(SearchContract.Event.OnSearch) },
            )

            FilterBar(
                modifier = Modifier.constrainAs(filterBar) {
                    top.linkTo(searchBar.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                selectedFilter = state.selectedFilter,
                onSelectAll = { viewModel.setEvent(SearchContract.Event.OnSelectAll) },
                onSelectCompetitions = { viewModel.setEvent(SearchContract.Event.OnSelectCompetitions) },
                onSelectParticipants = { viewModel.setEvent(SearchContract.Event.OnSelectParticipants) },
            )

            SearchList(
                modifier = Modifier.constrainAs(searchList) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(filterBar.bottom, 8.dp)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                state = searchListState,
                onItemClick = onNavigateToDetail,
                resultItems = state.items,
            )
        }
    }

    LaunchedEffect(true) {
        viewModel.effect.collectLatest {
            when (it) {
                SearchContract.Effect.HideKeyboard -> keyboardController?.hide()
                SearchContract.Effect.ScrollResultToTop -> searchListState.scrollToItem(0)
            }
        }
    }
}



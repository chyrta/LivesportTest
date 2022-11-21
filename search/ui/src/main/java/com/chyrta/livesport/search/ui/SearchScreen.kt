package com.chyrta.livesport.search.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.search.logic.presentation.SearchContract
import com.chyrta.livesport.search.logic.presentation.SearchViewModel
import com.chyrta.livesport.search.ui.components.FilterBar
import com.chyrta.livesport.search.ui.components.SearchBar
import com.chyrta.livesport.search.ui.components.SearchList
import kiwi.orbit.compose.ui.controls.ButtonPrimary
import kiwi.orbit.compose.ui.controls.EmptyState
import kiwi.orbit.compose.ui.controls.InlineLoading
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TopAppBar
import kiwi.orbit.compose.ui.utils.plus
import kotlinx.coroutines.flow.collectLatest
import com.chyrta.livesport.common.R as CommonR
import kiwi.orbit.compose.illustrations.R as KiwiIllustrationR

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
            TopAppBar(title = { Text(stringResource(id = CommonR.string.navigation_bar_title)) })
        },
    ) { contentPadding ->
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .padding(contentPadding + PaddingValues(16.dp)),
        ) {
            val (searchBar, filterBar, container) = createRefs()

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
                onSelectFilter = { viewModel.setEvent(SearchContract.Event.OnSelectFilter(it)) }
            )

            Box(
                modifier = Modifier.constrainAs(container) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(filterBar.bottom, 8.dp)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                contentAlignment = Alignment.Center,
            ) {
                when {
                    state.emptyState -> EmptyState(
                        illustration = painterResource(id = KiwiIllustrationR.drawable.il_orbit_sports_equipment),
                        title = stringResource(id = CommonR.string.start_search_now),
                    )
                    state.hasError -> EmptyState(
                        illustration = painterResource(id = KiwiIllustrationR.drawable.il_orbit_error),
                        title = state.errorState!!.title,
                        description = state.errorState!!.message,
                        action = {
                            ButtonPrimary(onClick = {
                                viewModel.setEvent(SearchContract.Event.OnSearch)
                            }) {
                                Text(stringResource(id = CommonR.string.try_again))
                            }
                        },
                    )
                    state.isLoadingResults -> InlineLoading()
                    state.hasResults -> SearchList(
                        modifier = Modifier.fillMaxSize(),
                        state = searchListState,
                        onItemClick = onNavigateToDetail,
                        resultItems = state.items
                    )
                    state.hasNoResults -> EmptyState(
                        illustration = painterResource(id = KiwiIllustrationR.drawable.il_orbit_no_results),
                        title = stringResource(id = CommonR.string.no_results),
                        description = stringResource(id = CommonR.string.try_different_query),
                    )
                }
            }
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



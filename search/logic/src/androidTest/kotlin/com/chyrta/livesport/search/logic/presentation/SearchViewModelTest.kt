package com.chyrta.livesport.search.logic.presentation

import app.cash.turbine.test
import app.cash.turbine.testIn
import com.chyrta.livesport.common.domain.MainDispatcher
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.search.logic.domain.model.SportEntity
import com.chyrta.livesport.search.logic.domain.usecase.GetSearchResultUseCase
import com.chyrta.livesport.search.logic.presentation.generator.generateMockedList
import com.chyrta.livesport.search.logic.presentation.generator.generateSearchEntity
import com.chyrta.livesport.search.logic.presentation.generator.generateSportEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.createTestCoroutineScope
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.junit5.KoinTestExtension

class SearchViewModelTest: KoinTest {

    private val searchResultUseCase = mockk<GetSearchResultUseCase>()

    private lateinit var underTest: SearchViewModel

    @JvmField
    @RegisterExtension
    val koinTestExtension = KoinTestExtension.create {
        modules(
            module {
                single { MainDispatcher() }
                single { searchResultUseCase }
            }
        )
    }

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        underTest = SearchViewModel()
    }

    @Test
    fun `Given search view model when enter search query then it should update query in state`() = runBlocking {
        val searchQuery = "hello"
        val event = SearchContract.Event.OnSearchQuery(searchQuery)
        underTest.event.test {
            underTest.setEvent(event)
            assertTrue(awaitItem() is SearchContract.Event.OnSearchQuery)
        }
        underTest.uiState.test {
            val updatedState = awaitItem()
            assertEquals(searchQuery, updatedState.query)
        }
    }

    @Test
    fun `Given search view model when tap on search button then it should start searching`() = runBlocking {
        val event = SearchContract.Event.OnSearch
        coEvery { searchResultUseCase(any(), any()) } coAnswers {
            delay(1000L)
            Result.success(emptyMap())
        }

        underTest.uiState.test {
            underTest.setEvent(event)
            skipItems(1)
            val updatedState = awaitItem()
            assertTrue(updatedState.isLoadingResults)
        }

        coVerify { searchResultUseCase(any(), any()) }
    }

    @Test
    fun `Given search view model when tap on search button then it should return results`() = runBlocking {
        val searchQuery = "Hello"
        val searchQueryEvent = SearchContract.Event.OnSearchQuery(searchQuery)
        val searchEvent = SearchContract.Event.OnSearch

        val resultsMap = mutableMapOf<SportEntity, List<SearchResultItemEntity>>().apply {
            val sportEntity = generateSportEntity()
            put(sportEntity, generateMockedList(10) { generateSearchEntity(sportEntity) })
        }

        coEvery { searchResultUseCase(any(), any()) } coAnswers {
            delay(1000L)
            Result.success(resultsMap)
        }

        underTest.uiState.test {
            underTest.setEvent(searchQueryEvent)
            skipItems(1)
            assertEquals(searchQuery, awaitItem().query)
            underTest.setEvent(searchEvent)
            assertTrue(awaitItem().isLoadingResults)
            assertEquals(resultsMap.keys.size, awaitItem().items.size)
        }

        coVerify { searchResultUseCase(any(), any()) }
    }

    @Test
    fun `Given search view model when tap on one of the filters it should update selected filter`() = runBlocking {
        val competitionsEvent = SearchContract.Event.OnSelectFilter(SearchFilter.Competitions)
        val participantsEvent = SearchContract.Event.OnSelectFilter(SearchFilter.Participants)

        val turbine1 = underTest.uiState.test {
            underTest.setEvent(competitionsEvent)
            underTest.setEvent(participantsEvent)
            val initialState = awaitItem()
            assertEquals(SearchContract.State.idle().selectedFilter, initialState.selectedFilter)
            val competitionsState = awaitItem()
            assertEquals(SearchFilter.Competitions, competitionsState.selectedFilter)
            val participantsState = awaitItem()
            assertEquals(SearchFilter.Participants, participantsState.selectedFilter)
        }
    }

    @Test
    fun `Given search view model when tap on clear search query button then it should remove results and search request`() = runBlocking {
        val searchQueryText = "Hello"
        val clearSearchQueryEvent = SearchContract.Event.OnClearQuery
        val searchQueryEvent = SearchContract.Event.OnSearchQuery(searchQueryText)

        underTest.uiState.test {
            underTest.setEvent(searchQueryEvent)
            skipItems(1)
            assertEquals(searchQueryText, awaitItem().query)
            underTest.setEvent(clearSearchQueryEvent)
            assertEquals("", awaitItem().query)
        }
    }

}


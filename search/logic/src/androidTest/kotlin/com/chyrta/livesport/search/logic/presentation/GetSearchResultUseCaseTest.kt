package com.chyrta.livesport.search.logic.presentation

import com.chyrta.livesport.common.RepositoryResult
import com.chyrta.livesport.common.util.Either
import com.chyrta.livesport.search.logic.data.repository.SearchRepository
import com.chyrta.livesport.search.logic.domain.model.GetSearchResultError
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.domain.usecase.GetSearchResultUseCase
import com.chyrta.livesport.search.logic.presentation.generator.generateErrorEntity
import com.chyrta.livesport.search.logic.presentation.generator.generateMockedList
import com.chyrta.livesport.search.logic.presentation.generator.generateSearchEntity
import com.chyrta.livesport.search.logic.presentation.generator.generateSportEntity
import io.mockk.coEvery
import io.mockk.mockk
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetSearchResultUseCaseTest {

    private val searchRepository = mockk<SearchRepository>()

    private lateinit var underTest: GetSearchResultUseCase

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        underTest = GetSearchResultUseCase(searchRepository)
    }

    @Test
    fun `Given get search result use case when search then it should return results`() = runBlocking {
        val sportOne = generateSportEntity()
        val sportTwo = generateSportEntity()
        val mockedList =
            generateMockedList(3) { generateSearchEntity(sportOne) } + generateMockedList(3) {
                generateSearchEntity(sportTwo)
            }

        coEvery { searchRepository.search(any(), any()) } returns RepositoryResult.Success(mockedList)

        val result = underTest("Hello", SearchFilter.All)

        assertTrue(result is Either.Left)

        val items = result.value
        assertNotNull(items)
        assertEquals(2, items.keys.size)
    }

    @Test
    fun `Given get search result use case when search then it should return network error`() = runBlocking {
        coEvery {
            searchRepository.search(
                any(),
                any(),
            )
        } returns RepositoryResult.Failure.NetworkFailure(IOException("No network conection"))

        val result = underTest("Hello", SearchFilter.All)
        assertTrue(result is Either.Right)
        assertTrue(result.value is GetSearchResultError.NetworkError)
    }

    @Test
    fun `Given get search result use case when search then it should missing parameters error`() = runBlocking {
        val mockError = generateErrorEntity(101)

        coEvery {
            searchRepository.search(
                any(),
                any(),
            )
        } returns RepositoryResult.Failure.ApiFailure(400, mockError)

        val result = underTest("Hello", SearchFilter.All)
        assertTrue(result is Either.Right)
        assertTrue(result.value is GetSearchResultError.MissingRequestParameters)
    }
}

package com.chyrta.livesport.search.logic.presentation

import com.chyrta.livesport.common.RepositoryResult
import com.chyrta.livesport.search.logic.data.repository.SearchRepository
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.domain.usecase.GetSearchResultUseCase
import com.chyrta.livesport.search.logic.presentation.generator.generateMockedList
import com.chyrta.livesport.search.logic.presentation.generator.generateSearchEntity
import com.chyrta.livesport.search.logic.presentation.generator.generateSportEntity
import io.mockk.coEvery
import io.mockk.mockk
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
        val mockedList = generateMockedList(3) { generateSearchEntity(sportOne) } + generateMockedList(3) { generateSearchEntity(sportTwo) }

        coEvery { searchRepository.search(any(), any()) } returns RepositoryResult.Success(mockedList)

        val result = underTest("Hello", SearchFilter.All)

        assertTrue(result.isSuccess)

        val items = result.getOrNull()
        assertNotNull(items)
        assertEquals(2, items.keys.size)
    }

}

package com.chyrta.livesport.search.logic.presentation

import com.chyrta.livesport.common.RepositoryResult
import com.chyrta.livesport.common.data.remote.model.ApiError
import com.chyrta.livesport.common.data.remote.model.ApiErrorItem
import com.chyrta.livesport.common.data.remote.model.ApiResult
import com.chyrta.livesport.search.logic.data.remote.SearchRemoteDataSource
import com.chyrta.livesport.search.logic.data.repository.SearchRepository
import com.chyrta.livesport.search.logic.data.repository.SearchRepositoryImpl
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.presentation.generator.generateApiSearchEntity
import com.chyrta.livesport.search.logic.presentation.generator.generateMockedList
import io.mockk.coEvery
import io.mockk.mockk
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchRepositoryTest {

    private val searchRemoteDataSource = mockk<SearchRemoteDataSource>(relaxed = true)

    private lateinit var underTest: SearchRepository

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        underTest = SearchRepositoryImpl(searchRemoteDataSource)
    }

    @Test
    fun `Given search repository when fetch results then it should return results from backend`() = runBlocking {
        val mockedList = generateMockedList(20) { generateApiSearchEntity() }

        coEvery { searchRemoteDataSource.search(any(), any()) } coAnswers { ApiResult.Success(mockedList) }

        val result = underTest.search("Hello", SearchFilter.Participants)

        assertFalse(result is RepositoryResult.Failure)
        assertTrue(result is RepositoryResult.Success)
        assertEquals(mockedList.size, result.data.size)
    }

    @Test
    fun `Given search repository when fetch results then it should return bad request error`() = runBlocking {
        val apiError = ApiError(
            code = 101,
            message = "One or more values are missing, see array of errors for details.",
            name = "BadRequestError",
            stack = "BadRequestError: One or more values are missing, see array of errors for details",
            errors = listOf(
                ApiErrorItem(
                    message = "q is required",
                    type = "any.required"
                )
            )
        )

        coEvery { searchRemoteDataSource.search(any(), any()) } coAnswers { ApiResult.Failure.ApiFailure(400, apiError) }

        val result = underTest.search("Hello", SearchFilter.Participants)

        assertTrue(result is RepositoryResult.Failure.ApiFailure)
        assertEquals(result.error?.code, apiError.code)
    }

    @Test
    fun `Given search repository when fetch results then it should return http code error`() = runBlocking {
        coEvery { searchRemoteDataSource.search(any(), any()) } coAnswers { ApiResult.Failure.HttpFailure(500) }
        val result = underTest.search("Hello", SearchFilter.Competitions)
        assertTrue(result is RepositoryResult.Failure.HttpFailure)
        assertEquals(result.statusCode, 500)
    }

    @Test
    fun `Given search repository when fetch results then it should return network error`() = runBlocking {
        coEvery { searchRemoteDataSource.search(any(), any()) } coAnswers { ApiResult.Failure.NetworkFailure(IOException("No internet connection")) }
        val result = underTest.search("Hello", SearchFilter.Competitions)
        assertTrue(result is RepositoryResult.Failure.NetworkFailure)
    }

    @Test
    fun `Given search repository when fetch results then it should return unknown error`() = runBlocking {
        coEvery { searchRemoteDataSource.search(any(), any()) } coAnswers { ApiResult.Failure.UnknownFailure(Error("Unknown error")) }
        val result = underTest.search("Hello", SearchFilter.All)
        assertTrue(result is RepositoryResult.Failure.UnknownFailure)
    }
}

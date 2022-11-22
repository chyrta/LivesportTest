package com.chyrta.livesport.search.logic.presentation

import com.chyrta.livesport.common.data.remote.model.ApiResult
import com.chyrta.livesport.search.logic.data.remote.SearchRemoteDataSource
import com.chyrta.livesport.search.logic.data.remote.SearchRemoteDataSourceImpl
import com.chyrta.livesport.search.logic.data.remote.model.ApiSearchFilter
import com.chyrta.livesport.search.logic.presentation.generator.LivesportApiMock
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlin.test.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchRemoteDataSourceTest {

    private val mockHttpClient = LivesportApiMock()

    private var httpClient = HttpClient(engine = mockHttpClient.engine) {
        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = true
                    ignoreUnknownKeys = true
                },
            )
        }
    }

    private lateinit var underTest: SearchRemoteDataSource

    @BeforeEach
    fun setup() {
        underTest = SearchRemoteDataSourceImpl(httpClient)
    }

    @Test
    fun `Given search remote data source when make request it should return results`() =
        runBlocking {
            val result = underTest.search(query = "ab", ApiSearchFilter.All)
            assertTrue(result is ApiResult.Success)
            assertTrue(result.value.isNotEmpty())
        }

    @Test
    fun `Given search remote data source when make request it should return error`() = runBlocking {
        val result = underTest.search(query = "a", ApiSearchFilter.All)
        assertTrue(result is ApiResult.Failure)
    }
}

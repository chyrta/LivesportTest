package com.chyrta.livesport.search.logic.data.remote

import com.chyrta.livesport.search.logic.data.remote.model.ApiSearchEntity
import com.chyrta.livesport.search.logic.data.remote.model.ApiSearchFilter
import com.chyrta.livesport.common.data.remote.model.ApiError
import com.chyrta.livesport.common.data.remote.model.ApiResponse
import com.chyrta.livesport.common.data.remote.model.ApiResult
import com.chyrta.livesport.common.util.safeRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class SearchRemoteDataSourceImpl(
    private val liveSportHttpClient: HttpClient,
) : SearchRemoteDataSource {

    override suspend fun search(
        query: String,
        filter: ApiSearchFilter,
    ): ApiResult<List<ApiSearchEntity>, ApiError> {
        val response = liveSportHttpClient.safeRequest<List<ApiSearchEntity>, ApiError> {
            url("search")
            parameter(LANGUAGE_ID, 1)
            parameter(PROJECT_ID, 602)
            parameter(PROJECT_TYPE_ID, 1)
            parameter(SPORT_IDS, (1..9).toList().joinToString(","))
            parameter(TYPE_IDS, filter.ids.joinToString(","))
            parameter(QUERY, query)
        }
        return when (response) {
            is ApiResponse.Success -> ApiResult.Success(response.body)
            is ApiResponse.Error.HttpError -> {
                when (response.code) {
                    400 -> ApiResult.Failure.ApiFailure(response.errorBody)
                    422 -> ApiResult.Failure.ApiFailure(response.errorBody)
                    503 -> ApiResult.Failure.ApiFailure(response.errorBody)
                    else -> ApiResult.Failure.UnknownFailure(Error("Unknown error"))
                }
            }
            is ApiResponse.Error.NetworkError -> ApiResult.Failure.NetworkFailure(response.e)
            is ApiResponse.Error.SerializationError -> ApiResult.Failure.UnknownFailure(response.e)
        }
    }

    private companion object {
        const val QUERY = "q"
        const val SPORT_IDS = "sport-ids"
        const val TYPE_IDS = "type-ids"
        const val PROJECT_ID = "project-id"
        const val LANGUAGE_ID = "lang-id"
        const val PROJECT_TYPE_ID = "project-type-id"
    }

}

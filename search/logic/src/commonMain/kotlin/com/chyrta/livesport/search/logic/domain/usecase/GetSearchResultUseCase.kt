package com.chyrta.livesport.search.logic.domain.usecase

import com.chyrta.livesport.search.logic.data.repository.SearchRepository
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.common.RepositoryResult
import com.chyrta.livesport.common.util.Either
import com.chyrta.livesport.search.logic.domain.model.GetSearchResultError
import com.chyrta.livesport.search.logic.domain.model.SportEntity

typealias GetSearchResult = Either<Map<SportEntity, List<SearchResultItemEntity>>, GetSearchResultError>

class GetSearchResultUseCase(
    private val searchRepository: SearchRepository,
) {
    suspend operator fun invoke(
        query: String,
        searchFilter: SearchFilter,
    ): GetSearchResult {
        return when (val result = (searchRepository.search(query, searchFilter))) {
            is RepositoryResult.Success -> Either.Left(result.data.groupBy { it.sport })
            is RepositoryResult.Failure.ApiFailure -> {
                val error = when (result.error?.code) {
                    MissingParametersInternalErrorCode -> GetSearchResultError.MissingRequestParameters
                    InvalidParametersInternalErrorCode -> GetSearchResultError.InvalidRequestParameters
                    ServiceUnavailableInternalErrorCode -> GetSearchResultError.ServiceUnavailable
                    else -> GetSearchResultError.HttpError(result.statusCode)
                }
                Either.Right(error)
            }
            is RepositoryResult.Failure.HttpFailure -> Either.Right(
                GetSearchResultError.HttpError(result.statusCode)
            )
            is RepositoryResult.Failure.NetworkFailure -> Either.Right(
                GetSearchResultError.NetworkError
            )
            is RepositoryResult.Failure.UnknownFailure -> Either.Right(
                GetSearchResultError.UnknownError
            )
        }
    }

    companion object {
        const val MissingParametersInternalErrorCode = 101
        const val InvalidParametersInternalErrorCode = 100
        const val ServiceUnavailableInternalErrorCode = 110
    }

}

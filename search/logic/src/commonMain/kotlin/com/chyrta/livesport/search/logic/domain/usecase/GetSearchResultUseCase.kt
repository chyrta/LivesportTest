package com.chyrta.livesport.search.logic.domain.usecase

import com.chyrta.livesport.search.logic.data.repository.SearchRepository
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.common.RepositoryResult
import com.chyrta.livesport.search.logic.domain.model.SportEntity

class GetSearchResultUseCase(
    private val searchRepository: SearchRepository,
) {

    suspend operator fun invoke(
        query: String,
        searchFilter: SearchFilter,
    ): Result<Map<SportEntity, List<SearchResultItemEntity>>> {
        return when (val result = (searchRepository.search(query, searchFilter))) {
            is RepositoryResult.Success -> {
                Result.success(result.data.groupBy { it.sport })
            }
            is RepositoryResult.Failure.ApiFailure -> Result.failure(Error("Something"))
            is RepositoryResult.Failure.HttpFailure -> Result.failure(Error("Http failure"))
            is RepositoryResult.Failure.NetworkFailure -> Result.failure(Error("Network failure"))
            is RepositoryResult.Failure.UnknownFailure -> Result.failure(Error("Unknown failure"))
        }
    }

}

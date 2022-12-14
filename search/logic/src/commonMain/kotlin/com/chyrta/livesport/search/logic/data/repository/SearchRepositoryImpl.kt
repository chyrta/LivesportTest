package com.chyrta.livesport.search.logic.data.repository

import com.chyrta.livesport.common.RepositoryResult
import com.chyrta.livesport.common.data.remote.model.ApiResult
import com.chyrta.livesport.common.domain.mapper.mapToEntity
import com.chyrta.livesport.common.domain.model.ErrorEntity
import com.chyrta.livesport.search.logic.data.remote.SearchRemoteDataSource
import com.chyrta.livesport.search.logic.domain.mapper.mapToApiEntity
import com.chyrta.livesport.search.logic.domain.mapper.mapToSportEntity
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity

class SearchRepositoryImpl(
    private val searchRemoteDataSource: SearchRemoteDataSource,
) : SearchRepository {

    override suspend fun search(
        query: String,
        filter: SearchFilter,
    ): RepositoryResult<List<SearchResultItemEntity>, ErrorEntity> {
        return when (val result = searchRemoteDataSource.search(query, filter.mapToApiEntity())) {
            is ApiResult.Success -> RepositoryResult.Success(result.value.map { it.mapToSportEntity() })
            is ApiResult.Failure -> {
                return when (result) {
                    is ApiResult.Failure.ApiFailure -> RepositoryResult.Failure.ApiFailure(
                        result.statusCode,
                        result.error?.mapToEntity()
                    )
                    is ApiResult.Failure.HttpFailure -> RepositoryResult.Failure.HttpFailure(
                        result.statusCode
                    )
                    is ApiResult.Failure.NetworkFailure -> RepositoryResult.Failure.NetworkFailure(
                        result.error
                    )
                    is ApiResult.Failure.UnknownFailure -> RepositoryResult.Failure.UnknownFailure(
                        result.error
                    )
                }
            }
        }
    }
}

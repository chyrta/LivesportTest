package com.chyrta.livesport.search.logic.data.repository

import com.chyrta.livesport.common.RepositoryResult
import com.chyrta.livesport.common.domain.model.ErrorEntity
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity

interface SearchRepository {

    suspend fun search(query: String, filter: SearchFilter): RepositoryResult<List<SearchResultItemEntity>, ErrorEntity>
}

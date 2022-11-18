package com.chyrta.livesport.search.logic.data.remote

import com.chyrta.livesport.search.logic.data.remote.model.ApiSearchEntity
import com.chyrta.livesport.search.logic.data.remote.model.ApiSearchFilter
import com.chyrta.livesport.common.data.remote.model.ApiError
import com.chyrta.livesport.common.data.remote.model.ApiResult

interface SearchRemoteDataSource {
    suspend fun search(query: String, filter: ApiSearchFilter): ApiResult<List<ApiSearchEntity>, ApiError>
}

package com.chyrta.livesport.common.domain.mapper

import com.chyrta.livesport.common.data.remote.model.ApiError
import com.chyrta.livesport.common.domain.model.ErrorEntity

fun ApiError.mapToEntity(): ErrorEntity = ErrorEntity(
    code = code,
    message = message,
)

package com.chyrta.livesport.common.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val code: Int,
    val message: String,
    val name: String,
    val stack: String? = null,
    val errors: List<ApiErrorItem>
)

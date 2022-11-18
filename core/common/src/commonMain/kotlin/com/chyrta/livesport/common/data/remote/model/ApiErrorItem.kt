package com.chyrta.livesport.common.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorItem(
    val message: String,
    val type: String
)

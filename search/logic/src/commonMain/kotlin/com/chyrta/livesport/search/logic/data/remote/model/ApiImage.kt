package com.chyrta.livesport.search.logic.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiImage(
    val path: String? = null,
    val usageId: Int,
    val variantTypeId: Int
)

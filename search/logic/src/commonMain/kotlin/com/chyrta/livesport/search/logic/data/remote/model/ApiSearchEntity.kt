package com.chyrta.livesport.search.logic.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiSearchEntity(
    val id: String,
    val name: String,
    val gender: ApiId,
    val type: ApiId,
    val sport: ApiId,
    val defaultCountry: ApiId,
    val images: List<ApiImage>
)

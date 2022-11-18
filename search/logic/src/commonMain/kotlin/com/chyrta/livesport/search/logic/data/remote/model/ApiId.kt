package com.chyrta.livesport.search.logic.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiId(
    val id: Int,
    val name: String
)

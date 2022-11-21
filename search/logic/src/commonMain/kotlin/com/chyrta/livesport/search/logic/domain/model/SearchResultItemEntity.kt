package com.chyrta.livesport.search.logic.domain.model

import com.chyrta.livesport.search.logic.data.remote.model.ApiId

data class SearchResultItemEntity(
    val id: String,
    val name: String,
    val gender: GenderEntity,
    val type: ApiId,
    val sport: SportEntity,
    val defaultCountry: String,
    val image: String?
)

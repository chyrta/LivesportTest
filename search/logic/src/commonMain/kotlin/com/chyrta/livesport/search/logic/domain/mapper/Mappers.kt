package com.chyrta.livesport.search.logic.domain.mapper

import com.chyrta.livesport.common.data.remote.Constants
import com.chyrta.livesport.search.logic.data.remote.model.ApiId
import com.chyrta.livesport.search.logic.data.remote.model.ApiImage
import com.chyrta.livesport.search.logic.data.remote.model.ApiSearchEntity
import com.chyrta.livesport.search.logic.data.remote.model.ApiSearchFilter
import com.chyrta.livesport.search.logic.domain.model.GenderEntity
import com.chyrta.livesport.search.logic.domain.model.SearchFilter
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.search.logic.domain.model.SportEntity
import io.ktor.http.URLBuilder

fun ApiSearchEntity.mapToSportEntity() = SearchResultItemEntity(
    id = id,
    name = name,
    gender = gender.mapToGenderEntity(),
    type = type,
    sport = sport.mapToSportEntity(),
    defaultCountry = defaultCountry.name,
    image = images.mapToImageUrl(name),
)

fun List<ApiImage>.mapToImageUrl(name: String): String {
    val defaultImageFile = this.find { it.variantTypeId == 15 }?.path
    val alternativeUrl = URLBuilder(Constants.UI_AVATAR_IMAGE_URL).apply {
        parameters.append("name", name)
    }.buildString()
    return defaultImageFile
        ?.let { Constants.LIVESPORT_IMAGE_URL + defaultImageFile }
        ?: alternativeUrl
}

fun ApiId.mapToGenderEntity() = GenderEntity.values()[id - 1]

fun ApiId.mapToSportEntity() = SportEntity.values()[id - 1]

fun SearchFilter.mapToApiEntity(): ApiSearchFilter = when (this) {
    SearchFilter.All -> ApiSearchFilter.All
    SearchFilter.Competitions -> ApiSearchFilter.Competitions
    SearchFilter.Participants -> ApiSearchFilter.Participants
}

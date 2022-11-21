package com.chyrta.livesport.search.logic.domain.mapper

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
    val img = this.find { it.variantTypeId == 15 }?.path
    val alternativeUrl = URLBuilder("https://ui-avatars.com/api/$name").buildString()
    return img?.let { "https://www.livesport.cz/res/image/data/$img" } ?: alternativeUrl
}

fun ApiId.mapToGenderEntity() = GenderEntity.values()[id - 1]

fun ApiId.mapToSportEntity() = SportEntity.values()[id - 1]

fun SearchFilter.mapToApiEntity(): ApiSearchFilter = when (this) {
    SearchFilter.All -> ApiSearchFilter.All
    SearchFilter.Competitions -> ApiSearchFilter.Competitions
    SearchFilter.Participants -> ApiSearchFilter.Participants
}

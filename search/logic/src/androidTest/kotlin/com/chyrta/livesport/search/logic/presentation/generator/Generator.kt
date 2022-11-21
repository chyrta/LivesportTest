package com.chyrta.livesport.search.logic.presentation.generator

import com.chyrta.livesport.search.logic.data.remote.model.ApiId
import com.chyrta.livesport.search.logic.data.remote.model.ApiSearchEntity
import com.chyrta.livesport.search.logic.domain.model.GenderEntity
import com.chyrta.livesport.search.logic.domain.model.SearchResultItemEntity
import com.chyrta.livesport.search.logic.domain.model.SportEntity
import java.util.UUID
import kotlin.random.Random

fun generateRandomString() = UUID.randomUUID().toString()

fun generateRandomId() = Random.nextInt(1, 100)

fun generateRandomApiGenderId() = ApiId(
    id = Random.nextInt(1, GenderEntity.values().size),
    name = generateRandomString()
)

fun generateRandomApiSportId() = ApiId(
    id = Random.nextInt(1, SportEntity.values().size),
    name = generateRandomString()
)

fun generateRandomApiId() = ApiId(
    id = generateRandomId(),
    name = generateRandomString()
)

fun <T> generateMockedList(size: Int, creatorFunction: () -> T) = mutableListOf<T>().apply {
    for (i in 1..size) {
        add(creatorFunction.invoke())
    }
}

fun generateApiSearchEntity() = ApiSearchEntity(
    id = generateRandomString(),
    name = generateRandomString(),
    gender = generateRandomApiGenderId(),
    type = generateRandomApiId(),
    sport = generateRandomApiSportId(),
    defaultCountry = generateRandomApiId(),
    images = emptyList()
)

fun generateSportEntity() = SportEntity.values().random()

fun generateGenderEntity() = GenderEntity.values().random()

fun generateSearchEntity(sportEntity: SportEntity = generateSportEntity()) = SearchResultItemEntity(
    id = generateRandomString(),
    name = generateRandomString(),
    gender = generateGenderEntity(),
    type = generateRandomApiId(),
    sport = sportEntity,
    defaultCountry = generateRandomString(),
    image = generateRandomString()
)

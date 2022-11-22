package com.chyrta.livesport.common.domain.model

data class ErrorEntity(
    val code: Int,
    val title: String,
    val message: String,
)

package com.chyrta.livesport.search.logic.data.remote.model

enum class ApiSearchFilter(val ids: List<Int>) {
    Competitions(listOf(1)),
    Participants(listOf(2, 3, 4)),
    All(Competitions.ids + Participants.ids),
}

package com.chyrta.livesport.common.domain

import kotlinx.coroutines.CoroutineDispatcher

expect class MainDispatcher {
    val dispatcher: CoroutineDispatcher
}

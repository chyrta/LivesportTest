package com.chyrta.livesport

import com.chyrta.livesport.common.base.di.initKoin
import com.chyrta.livesport.search.logic.di.searchModule

fun startKoin() = initKoin {
    modules(
        searchModule
    )
}

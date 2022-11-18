package com.chyrta.livesport

import com.chyrta.livesport.search.logic.di.searchModule
import com.chyrta.livesport.common.base.di.dispatcherModule
import com.chyrta.livesport.common.base.di.initKoin
import com.chyrta.livesport.common.base.di.networkModule
import com.chyrta.livesport.common.base.di.platformModule

fun startKoin() {
    initKoin {
        modules(
            platformModule()
            networkModule,
            dispatcherModule,
            searchModule
        )
    }
}

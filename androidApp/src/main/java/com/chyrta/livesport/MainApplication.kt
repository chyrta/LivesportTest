package com.chyrta.livesport

import android.app.Application
import com.chyrta.livesport.search.logic.di.searchModule
import com.chyrta.livesport.common.base.di.initKoin
import com.chyrta.livesport.common.base.di.networkModule
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
            modules(searchModule)
        }

    }

}

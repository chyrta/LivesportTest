package com.chyrta.livesport.common.base.di

import com.chyrta.livesport.common.domain.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { MainDispatcher() }
}

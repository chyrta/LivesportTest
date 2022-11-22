package com.chyrta.livesport.search.logic.di

import com.chyrta.livesport.search.logic.data.remote.SearchRemoteDataSource
import com.chyrta.livesport.search.logic.data.remote.SearchRemoteDataSourceImpl
import com.chyrta.livesport.search.logic.data.repository.SearchRepository
import com.chyrta.livesport.search.logic.data.repository.SearchRepositoryImpl
import com.chyrta.livesport.search.logic.domain.usecase.GetSearchResultUseCase
import com.chyrta.livesport.search.logic.presentation.SearchDetailViewModel
import com.chyrta.livesport.search.logic.presentation.SearchViewModel
import org.koin.dsl.module

val searchModule = module {
    single<SearchRemoteDataSource> { SearchRemoteDataSourceImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single { GetSearchResultUseCase(get()) }
    single { SearchViewModel() }
    single { SearchDetailViewModel() }
}

package com.mindvalley.mindvalleyapptest.di

import com.mindvalley.mindvalleyapptest.domain.usecase.*
import com.mindvalley.mindvalleyapptest.ui.ChannelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<IGetChannelUseCase> { GetChannelUseCase(get()) }
    factory<IGetNewEpisodesUseCase> { GetNewEpisodeUseCase(get()) }
    factory<IGetCategoriesUseCase> { GetCategoriesUseCase(get()) }
}
val viewModelModule = module {
    viewModel { ChannelViewModel(get(), get(), get()) }
}

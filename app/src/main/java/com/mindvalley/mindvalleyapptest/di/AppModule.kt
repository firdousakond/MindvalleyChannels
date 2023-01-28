package com.mindvalley.mindvalleyapptest.di

import com.mindvalley.mindvalleyapptest.domain.usecase.GetCategoriesUseCase
import com.mindvalley.mindvalleyapptest.domain.usecase.GetChannelUseCase
import com.mindvalley.mindvalleyapptest.domain.usecase.GetNewEpisodeUseCase
import com.mindvalley.mindvalleyapptest.ui.ChannelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetChannelUseCase(get()) }
    factory { GetNewEpisodeUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
}
val viewModelModule = module {
    viewModel { ChannelViewModel(get(), get(), get()) }
}

package com.thomas.topheadlines.di

import com.thomas.topheadlines.presentation.viewmodel.HeadlineArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HeadlineArticlesViewModel(get()) }
}
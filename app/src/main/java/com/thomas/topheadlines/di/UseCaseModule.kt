package com.thomas.topheadlines.di

import com.thomas.topheadlines.domain.repository.ITopHeadlineRepository
import com.thomas.topheadlines.domain.usecase.ITopHeadlinesUseCase
import com.thomas.topheadlines.domain.usecase.TopHeadlinesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { buildHeadlinesUseCase(get()) }
}

fun buildHeadlinesUseCase(repository: ITopHeadlineRepository): ITopHeadlinesUseCase {
    return TopHeadlinesUseCase(repository)
}

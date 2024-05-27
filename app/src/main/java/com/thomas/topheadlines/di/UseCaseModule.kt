package com.thomas.topheadlines.di

import com.thomas.topheadlines.domain.pagingsource.ITopHeadlinesDataSource
import com.thomas.topheadlines.domain.pagingsource.TopHeadlinesDataSource
import com.thomas.topheadlines.domain.repository.ITopHeadlineRepository
import com.thomas.topheadlines.domain.usecase.ITopHeadlinesUseCase
import com.thomas.topheadlines.domain.usecase.TopHeadlinesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { buildTopHeadlineDataSource(get()) }
    factory { buildHeadlinesUseCase(get()) }
}

fun buildTopHeadlineDataSource(repository: ITopHeadlineRepository): ITopHeadlinesDataSource {
    return TopHeadlinesDataSource(repository)
}

fun buildHeadlinesUseCase(dataSource: ITopHeadlinesDataSource): ITopHeadlinesUseCase {
    return TopHeadlinesUseCase(dataSource)
}
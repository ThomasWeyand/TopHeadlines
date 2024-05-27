package com.thomas.topheadlines.di

import com.thomas.topheadlines.domain.repository.ITopHeadlineRepository
import com.thomas.topheadlines.data.repository.TopHeadlineRepository
import com.thomas.topheadlines.data.service.HeadlineApi
import org.koin.dsl.module

val repositoryModule = module {
    factory { buildTopHeadlinesRepository(get()) }
}

fun buildTopHeadlinesRepository(api: HeadlineApi): ITopHeadlineRepository {
    return TopHeadlineRepository(api)
}
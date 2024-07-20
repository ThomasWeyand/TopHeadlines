package com.thomas.topheadlines.di

import androidx.paging.PagingSource
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.domain.usecase.ITopHeadlinesUseCase
import com.thomas.topheadlines.presentation.paging.IPagingFactory
import com.thomas.topheadlines.presentation.paging.PagingFactory
import com.thomas.topheadlines.presentation.paging.TopHeadlinesPagingSource
import org.koin.dsl.module

val pagingSourceModule = module {
    factory { buildPagingSource(get()) }
    factory { buildPagingFactory(get()) }
}

fun buildPagingFactory(pagingSource: PagingSource<Int, SealedArticleResult>): IPagingFactory {
    return PagingFactory(pagingSource)
}

fun buildPagingSource(useCase: ITopHeadlinesUseCase): PagingSource<Int, SealedArticleResult> {
    return TopHeadlinesPagingSource(useCase)
}

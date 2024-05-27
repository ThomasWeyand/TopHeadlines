package com.thomas.topheadlines.domain.usecase

import androidx.paging.PagingData
import com.thomas.topheadlines.domain.model.SealedArticleResult
import kotlinx.coroutines.flow.Flow

interface ITopHeadlinesUseCase {
    fun paginateData(): Flow<PagingData<SealedArticleResult>>
}
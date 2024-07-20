package com.thomas.topheadlines.presentation.paging

import androidx.paging.PagingData
import com.thomas.topheadlines.domain.model.SealedArticleResult
import kotlinx.coroutines.flow.Flow

interface IPagingFactory {
    fun paginateData(): Flow<PagingData<SealedArticleResult>>
}

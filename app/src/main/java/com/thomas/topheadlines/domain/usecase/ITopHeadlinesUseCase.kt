package com.thomas.topheadlines.domain.usecase

import com.thomas.topheadlines.data.model.TopHeadlineRequest
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.domain.model.SealedResult

interface ITopHeadlinesUseCase {
    suspend fun fetchData(request: TopHeadlineRequest): SealedResult<List<SealedArticleResult>>
}
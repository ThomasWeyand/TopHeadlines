package com.thomas.topheadlines.domain.repository

import com.thomas.topheadlines.data.model.TopHeadlineRequest
import com.thomas.topheadlines.data.model.TopHeadlinesResultResponse

interface ITopHeadlineRepository {
    suspend fun fetchTopHeadlines(request: TopHeadlineRequest): TopHeadlinesResultResponse
}
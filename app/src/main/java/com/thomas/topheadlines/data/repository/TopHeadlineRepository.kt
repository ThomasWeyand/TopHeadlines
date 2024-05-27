package com.thomas.topheadlines.data.repository

import com.thomas.topheadlines.data.model.TopHeadlineRequest
import com.thomas.topheadlines.data.model.TopHeadlinesResultResponse
import com.thomas.topheadlines.data.service.HeadlineApi
import com.thomas.topheadlines.domain.repository.ITopHeadlineRepository

internal class TopHeadlineRepository(private val api: HeadlineApi) : ITopHeadlineRepository {

    override suspend fun fetchTopHeadlines(request: TopHeadlineRequest): TopHeadlinesResultResponse {
        return api.fetchTopHeadlines(page = request.page, pageSize = request.pageSize)
    }

}
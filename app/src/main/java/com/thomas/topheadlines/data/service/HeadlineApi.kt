package com.thomas.topheadlines.data.service

import com.thomas.topheadlines.data.model.TopHeadlinesResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlineApi {

    @GET("top-headlines")
    suspend fun fetchTopHeadlines(
        @Query("sources") sources: String = "bbc-news",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): TopHeadlinesResultResponse

}
package com.thomas.topheadlines.data.model

import com.google.gson.annotations.SerializedName

data class TopHeadlinesResultResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int?,
    @SerializedName("code") val code: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("articles") val articles: List<ArticleResponse> = emptyList(),
)

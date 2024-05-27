package com.thomas.topheadlines

import com.thomas.topheadlines.data.model.ArticleResponse
import com.thomas.topheadlines.data.model.ArticleSourceResponse
import com.thomas.topheadlines.data.model.TopHeadlineRequest
import com.thomas.topheadlines.data.model.TopHeadlinesResultResponse
import com.thomas.topheadlines.domain.model.ArticleSource
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.domain.model.SealedResult

object MockUtils {
    fun getMockTopHeadlinesSuccess() = TopHeadlinesResultResponse(
        status = "ok",
        totalResults = null,
        code = null,
        message = null,
        articles = listOf(
            ArticleResponse(
                source = ArticleSourceResponse(
                    id = "bbc-news",
                    name = "BBC News"
                ),
                author = null,
                title = "title test",
                description = "description test",
                url = "url test",
                urlToImage = "url image test",
                publishedAt = "2024-05-27T04:52:13.1297055Z",
                content = "content test"
            ),
            ArticleResponse(
                source = ArticleSourceResponse(
                    id = "bbc-news",
                    name = "BBC News"
                ),
                author = null,
                title = "title test",
                description = "description test",
                url = "url test",
                urlToImage = "url image test",
                publishedAt = "2024-05-27T04:52:13.1297055Z",
                content = "content test"
            )
        )
    )

    fun getMockTopHeadlinesError() = TopHeadlinesResultResponse(
        status = "error",
        totalResults = null,
        code = "1",
        message = null,
    )

    fun getMockSealedArticlesSuccess() = SealedResult.Success(listOf(
        SealedArticleResult.Published(publishedAt = "2024-05-27"),
        SealedArticleResult.Article(
            source = ArticleSource(
                id = "bbc-news",
                name = "BBC News"
            ),
            author = "",
            title = "title test",
            description = "description test",
            url = "url test",
            urlToImage = "url image test",
            content = "content test"
        ),
        SealedArticleResult.Article(
            source = ArticleSource(
                id = "bbc-news",
                name = "BBC News"
            ),
            author = "",
            title = "title test",
            description = "description test",
            url = "url test",
            urlToImage = "url image test",
            content = "content test"
        )
    ))

    fun getMockRequest() = TopHeadlineRequest(page = 1)

}

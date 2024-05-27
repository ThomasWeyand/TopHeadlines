package com.thomas.topheadlines.domain.mapper

import com.thomas.topheadlines.data.model.ArticleResponse
import com.thomas.topheadlines.data.model.ArticleSourceResponse
import com.thomas.topheadlines.domain.model.ArticleSource
import com.thomas.topheadlines.domain.model.SealedArticleResult

fun ArticleResponse.mapToDomain() = SealedArticleResult.Article(
    source = source.mapToDomain(),
    author = author.orEmpty(),
    title = title,
    description = description.orEmpty(),
    url = url,
    urlToImage = urlToImage.orEmpty(),
    content = content.orEmpty()
)

fun ArticleSourceResponse.mapToDomain() = ArticleSource(
    id = id.orEmpty(),
    name = name.orEmpty()
)
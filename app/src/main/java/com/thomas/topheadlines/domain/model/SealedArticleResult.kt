package com.thomas.topheadlines.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class SealedArticleResult(val id: String) : Parcelable {

    @Parcelize
    data class Article(
        val source: ArticleSource,
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val content: String,
    ) : SealedArticleResult(source.id)

    @Parcelize
    data class Published(val publishedAt: String) : SealedArticleResult(publishedAt)
}

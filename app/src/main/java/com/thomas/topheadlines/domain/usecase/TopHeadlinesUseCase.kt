package com.thomas.topheadlines.domain.usecase

import com.thomas.topheadlines.data.model.TopHeadlineRequest
import com.thomas.topheadlines.domain.mapper.mapToDomain
import com.thomas.topheadlines.domain.model.ErrorResult
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.domain.model.SealedResult
import com.thomas.topheadlines.domain.repository.ITopHeadlineRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal class TopHeadlinesUseCase(private val repository: ITopHeadlineRepository) :
    ITopHeadlinesUseCase {

    private val resumedDateFormat by lazy {
        SimpleDateFormat(
            RESUMED_DATE_PATTERN,
            Locale.US
        )
    }
    private var publishedAt: Date? = null

    override suspend fun fetchData(request: TopHeadlineRequest): SealedResult<List<SealedArticleResult>> {
        try {
            val result = repository.fetchTopHeadlines(request)
            if (result.status == ERROR_STATUS) {
                return SealedResult.Error(
                    ErrorResult(
                        message = result.message.orEmpty(),
                        code = result.code.orEmpty()
                    )
                )
            } else {
                val mutableArticleList = mutableListOf<SealedArticleResult>()
                result.articles.forEach { article ->
                    if (haveChangedDate(article.publishedAt)) {
                        publishedAt?.let {
                            mutableArticleList.add(
                                SealedArticleResult.Published(
                                    resumedDateFormat.format(it)
                                )
                            )
                        }
                    }
                    mutableArticleList.add(article.mapToDomain())
                }
                publishedAt = null
                return SealedResult.Success(mutableArticleList)
            }
        } catch (ex: Exception) {
            return SealedResult.Error(ErrorResult(message = ex.message.orEmpty()))
        }
    }

    private fun haveChangedDate(date: String): Boolean {
        val oldDate = resumedDateFormat.parse(date)
        return if (publishedAt == null) {
            oldDate?.let { publishedAt = it }
            true
        } else {
            if (oldDate?.after(publishedAt) == true) {
                publishedAt = oldDate
                true
            } else {
                false
            }
        }
    }

    companion object {
        private const val ERROR_STATUS = "error"
        private const val RESUMED_DATE_PATTERN = "yyyy-MM-dd"
    }
}

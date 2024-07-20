package com.thomas.topheadlines.presentation.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.utils.Constant
import kotlinx.coroutines.flow.Flow

internal class PagingFactory(private val pagingSource: PagingSource<Int, SealedArticleResult>) : IPagingFactory {

    override fun paginateData(): Flow<PagingData<SealedArticleResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pagingSource }
        ).flow
    }
}

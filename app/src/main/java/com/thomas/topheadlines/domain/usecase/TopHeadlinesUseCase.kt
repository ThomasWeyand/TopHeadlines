package com.thomas.topheadlines.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.domain.pagingsource.ITopHeadlinesDataSource
import com.thomas.topheadlines.domain.pagingsource.TopHeadlinesPagingSource
import com.thomas.topheadlines.utils.Constant
import kotlinx.coroutines.flow.Flow

internal class TopHeadlinesUseCase(private val dataSource: ITopHeadlinesDataSource) :
    ITopHeadlinesUseCase {

    override fun paginateData(): Flow<PagingData<SealedArticleResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopHeadlinesPagingSource(dataSource) }
        ).flow
    }

}
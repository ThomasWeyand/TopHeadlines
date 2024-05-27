package com.thomas.topheadlines.domain.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thomas.topheadlines.data.model.TopHeadlineRequest
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.domain.model.SealedResult
import com.thomas.topheadlines.utils.Constant

internal class TopHeadlinesPagingSource(private val dataSource: ITopHeadlinesDataSource) :
    PagingSource<Int, SealedArticleResult>() {

    override fun getRefreshKey(state: PagingState<Int, SealedArticleResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SealedArticleResult> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return when (val fetchData =
            dataSource.fetchData(TopHeadlineRequest(position, Constant.PAGE_SIZE))) {
            is SealedResult.Success -> {
                LoadResult.Page(
                    data = fetchData.data,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (fetchData.data.isEmpty()) null else position + 1
                )
            }

            is SealedResult.Error -> {
                LoadResult.Error(Throwable(message = fetchData.error.message))
            }
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}

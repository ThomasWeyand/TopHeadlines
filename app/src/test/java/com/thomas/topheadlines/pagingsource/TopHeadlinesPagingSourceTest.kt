package com.thomas.topheadlines.pagingsource

import androidx.paging.PagingSource
import com.thomas.topheadlines.MockUtils
import com.thomas.topheadlines.domain.model.ErrorResult
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.domain.model.SealedResult
import com.thomas.topheadlines.domain.pagingsource.ITopHeadlinesDataSource
import com.thomas.topheadlines.domain.pagingsource.TopHeadlinesPagingSource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TopHeadlinesPagingSourceTest {
    private lateinit var dataSource: ITopHeadlinesDataSource
    private lateinit var pagingSource: TopHeadlinesPagingSource

    @Before
    fun setUp() {
        dataSource = mockk(relaxed = true)
        pagingSource = TopHeadlinesPagingSource(dataSource)
    }

    @Test
    fun load_returns_page_when_successful() = runBlocking {
        val mockSuccess = MockUtils.getMockSealedArticlesSuccess()
        coEvery { dataSource.fetchData(MockUtils.getMockRequest()) } returns mockSuccess

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        val expected = PagingSource.LoadResult.Page(
            data = mockSuccess.data,
            prevKey = null,
            nextKey = 2
        )
        assertEquals(expected, result)
    }

    @Test
    fun load_returns_error_when_exception_thrown() = runBlocking {
        val exception = Throwable("Something went wrong")
        coEvery { dataSource.fetchData(MockUtils.getMockRequest()) } returns SealedResult.Error(
            ErrorResult(message = exception.message.orEmpty())
        )

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val expected = PagingSource.LoadResult.Error<Int, SealedArticleResult>(exception)
        assertEquals(expected.throwable.message, (result as PagingSource.LoadResult.Error<Int, SealedArticleResult>).throwable.message)
    }

}
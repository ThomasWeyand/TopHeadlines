package com.thomas.topheadlines.pagingsource

import com.thomas.topheadlines.MockUtils
import com.thomas.topheadlines.data.repository.TopHeadlineRepository
import com.thomas.topheadlines.data.service.HeadlineApi
import com.thomas.topheadlines.domain.model.ErrorResult
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.domain.model.SealedResult
import com.thomas.topheadlines.domain.pagingsource.ITopHeadlinesDataSource
import com.thomas.topheadlines.domain.pagingsource.TopHeadlinesDataSource
import com.thomas.topheadlines.domain.repository.ITopHeadlineRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TopHeadlinesDataSourceTest {

    private lateinit var apiService: HeadlineApi
    private lateinit var repository: ITopHeadlineRepository
    private lateinit var dataSource: ITopHeadlinesDataSource

    @Before
    fun setUp() {
        apiService = mockk(relaxed = true)
        repository = TopHeadlineRepository(apiService)
        dataSource = TopHeadlinesDataSource(repository)
    }

    @Test
    fun verify_error_return() = runBlocking {
        val request = MockUtils.getMockRequest()
        val errorResponse = MockUtils.getMockTopHeadlinesError()
        coEvery {
            repository.fetchTopHeadlines(
                request
            )
        } returns errorResponse
        val errorResult: SealedResult<List<SealedArticleResult>> = SealedResult.Error(ErrorResult(code = "1"))
        val result = dataSource.fetchData(request)
        assertEquals(
            (result as SealedResult.Error).error.code,
            (errorResult as SealedResult.Error).error.code
        )
    }

    @Test
    fun verify_success_return() = runBlocking {
        val request = MockUtils.getMockRequest()
        val successResponse = MockUtils.getMockTopHeadlinesSuccess()
        coEvery {
            repository.fetchTopHeadlines(
                request
            )
        } returns successResponse
        val result = dataSource.fetchData(request)
        assertEquals(MockUtils.getMockSealedArticlesSuccess(), (result as SealedResult.Success))
    }

}

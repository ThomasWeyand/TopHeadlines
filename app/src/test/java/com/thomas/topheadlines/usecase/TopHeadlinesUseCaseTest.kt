package com.thomas.topheadlines.usecase

import com.thomas.topheadlines.MockUtils
import com.thomas.topheadlines.data.repository.TopHeadlineRepository
import com.thomas.topheadlines.data.service.HeadlineApi
import com.thomas.topheadlines.domain.model.ErrorResult
import com.thomas.topheadlines.domain.model.SealedArticleResult
import com.thomas.topheadlines.domain.model.SealedResult
import com.thomas.topheadlines.domain.repository.ITopHeadlineRepository
import com.thomas.topheadlines.domain.usecase.ITopHeadlinesUseCase
import com.thomas.topheadlines.domain.usecase.TopHeadlinesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TopHeadlinesUseCaseTest {

    private lateinit var apiService: HeadlineApi
    private lateinit var repository: ITopHeadlineRepository
    private lateinit var useCase: ITopHeadlinesUseCase

    @Before
    fun setUp() {
        apiService = mockk(relaxed = true)
        repository = TopHeadlineRepository(apiService)
        useCase = TopHeadlinesUseCase(repository)
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
        val result = useCase.fetchData(request)
        TestCase.assertEquals(
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
        val result = useCase.fetchData(request)
        TestCase.assertEquals(MockUtils.getMockSealedArticlesSuccess(), (result as SealedResult.Success))
    }

}

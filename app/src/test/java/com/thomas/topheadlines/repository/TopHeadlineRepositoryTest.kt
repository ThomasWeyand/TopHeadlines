package com.thomas.topheadlines.repository

import com.thomas.topheadlines.MockUtils
import com.thomas.topheadlines.data.model.TopHeadlineRequest
import com.thomas.topheadlines.data.repository.TopHeadlineRepository
import com.thomas.topheadlines.data.service.HeadlineApi
import com.thomas.topheadlines.domain.repository.ITopHeadlineRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TopHeadlineRepositoryTest {

    private lateinit var apiService: HeadlineApi
    private lateinit var repository: ITopHeadlineRepository

    @Before
    fun setUp() {
        apiService = mockk(relaxed = true)
        repository = TopHeadlineRepository(apiService)
    }

    @Test
    fun fetch_top_headlines() = runBlocking {
        val successResponse = MockUtils.getMockTopHeadlinesSuccess()
        val request = MockUtils.getMockRequest()
        coEvery {
            apiService.fetchTopHeadlines(
                page = request.page,
                pageSize = request.pageSize
            )
        } returns successResponse
        val actualResponse = repository.fetchTopHeadlines(request)
        assertEquals(successResponse, actualResponse)
        coVerify(atLeast = 1) {
            apiService.fetchTopHeadlines(
                page = request.page,
                pageSize = request.pageSize
            )
        }
    }

}

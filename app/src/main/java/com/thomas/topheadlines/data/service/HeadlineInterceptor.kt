package com.thomas.topheadlines.data.service

import com.thomas.topheadlines.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class HeadlineInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder()
                .header(API_KEY_HEADER, BuildConfig.API_KEY)
                .build()
        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY_HEADER = "X-Api-Key"
    }
}
package com.thomas.topheadlines.di

import com.thomas.topheadlines.BuildConfig
import com.thomas.topheadlines.data.service.HeadlineApi
import com.thomas.topheadlines.data.service.HeadlineInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit(get()) }
    single { provideOkhttpClient() }
    single { headlineApi(get()) }
}

fun headlineApi(retrofit: Retrofit): HeadlineApi {
    return retrofit.create(HeadlineApi::class.java)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkhttpClient(
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HeadlineInterceptor())
        .build()
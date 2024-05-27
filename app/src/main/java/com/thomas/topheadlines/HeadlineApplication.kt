package com.thomas.topheadlines

import android.app.Application
import com.thomas.topheadlines.di.networkModule
import com.thomas.topheadlines.di.repositoryModule
import com.thomas.topheadlines.di.useCaseModule
import com.thomas.topheadlines.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class HeadlineApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HeadlineApplication)
            modules(networkModule, viewModelModule, repositoryModule, useCaseModule)
        }
    }

}
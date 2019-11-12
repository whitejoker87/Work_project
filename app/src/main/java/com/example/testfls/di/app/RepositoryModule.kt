package com.example.testfls.di.app

import com.example.testfls.model.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun repository(dao: NewsDao, provider: RssProvider): NewsRepository =
        NewsRepository(dao,provider)

    @Singleton
    @Provides
    fun provider(api: RssApi): RssProvider =
        RssProvider(api)

}
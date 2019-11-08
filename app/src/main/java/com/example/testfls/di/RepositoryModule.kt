package com.example.testfls.di

import android.content.Context
import androidx.room.Room
import com.example.testfls.model.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun repository(dao: NewsDao, provider: RssProvider): NewsRepository =
        NewsRepository(dao,provider)

    @Singleton
    @Provides
    fun provider(api: RssApi): RssProvider =
        RssProvider(api)

}
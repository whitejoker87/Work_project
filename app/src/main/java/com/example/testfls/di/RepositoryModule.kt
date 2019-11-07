package com.example.testfls.di

import android.content.Context
import androidx.room.Room
import com.example.testfls.model.NewsDao
import com.example.testfls.model.NewsRepository
import com.example.testfls.model.NewsRoomDatabase
import com.example.testfls.model.RssProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class RepositoryModule {

    @Provides
    fun repository(dao: NewsDao, provider: RssProvider): NewsRepository =
        NewsRepository(dao,provider)

    @Singleton
    @Provides
    fun dao(database: NewsRoomDatabase): NewsDao = database.newsDao()

    @Singleton
    @Provides
    fun database(context: Context): NewsRoomDatabase =
        Room.databaseBuilder(context, NewsRoomDatabase::class.java, "news_database").build()

    @Singleton
    @Provides
    fun provider(): RssProvider = RssProvider()

}
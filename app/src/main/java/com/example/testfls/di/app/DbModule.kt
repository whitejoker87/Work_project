package com.example.testfls.di.app

import android.app.Application
import androidx.room.Room
import com.example.testfls.model.NewsDao
import com.example.testfls.model.NewsRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideDao(database: NewsRoomDatabase): NewsDao = database.newsDao()

    @Singleton
    @Provides
    fun provideDatabase(application: Application): NewsRoomDatabase =
        Room.databaseBuilder(application, NewsRoomDatabase::class.java, "news_database").build()
//        NewsRoomDatabase.getDatabase(application)

}
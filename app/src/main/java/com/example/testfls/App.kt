package com.example.testfls

import android.app.Application
import com.example.testfls.model.NewsDao
import com.example.testfls.model.NewsRepository
import com.example.testfls.model.NewsRoomDatabase

class App: Application() {


    override fun onCreate() {
        super.onCreate()

        dao = NewsRoomDatabase.getDatabase(applicationContext).newsDao()
        repository = NewsRepository()

   }
    companion object {
        var dao: NewsDao? = null
            private set
        var repository: NewsRepository? = null
    }
}
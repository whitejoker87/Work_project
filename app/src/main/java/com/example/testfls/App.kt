package com.example.testfls

import android.app.Application
import com.example.testfls.model.NewsDao
import com.example.testfls.model.NewsRoomDatabase

class App: Application() {


    override fun onCreate() {
        super.onCreate()

        dao = NewsRoomDatabase.getDatabase(applicationContext).newsDao()

   }
    companion object {
        var dao: NewsDao? = null
            private set
    }
}
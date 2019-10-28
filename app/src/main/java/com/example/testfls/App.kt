package com.example.testfls

import android.app.Application
import androidx.room.Room
import com.example.testfls.model.NewsDao
import com.example.testfls.model.NewsRepository
import com.example.testfls.model.NewsRoomDatabase

class App: Application() {

//    private var database: NewsRoomDatabase? = null//


    override fun onCreate() {
        super.onCreate()

//        instance = this//
//        database = Room.databaseBuilder(this, NewsRoomDatabase::class.java, "database").build()//

        dao = NewsRoomDatabase.getDatabase(applicationContext).newsDao()
        repository = NewsRepository()

   }
    companion object {
//        var instance: App? = null//
        var dao: NewsDao? = null
            private set
        var repository: NewsRepository? = null
    }
}
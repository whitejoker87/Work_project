package com.example.testfls

import android.app.Application
import androidx.room.Room
import com.example.testfls.di.ContextModule
import com.example.testfls.di.DaggerRepositoryComponent
import com.example.testfls.di.RepositoryModule
import com.example.testfls.model.NewsDao
import com.example.testfls.model.NewsRepository
import com.example.testfls.model.NewsRoomDatabase
import com.example.testfls.model.RssProvider

class App: Application() {

    override fun onCreate() {
        super.onCreate()

//        val repositoryComponent = DaggerRepositoryComponent.builder().repositoryModule(RepositoryModule()).contextModule(ContextModule(this)).build()
//
//        val dao = NewsRoomDatabase.getDatabase(applicationContext).newsDao()
//        val provider = RssProvider()
//        repository = NewsRepository(dao,provider)

   }
//    companion object {
//        var repository: NewsRepository? = null
//            private set
//    }
}
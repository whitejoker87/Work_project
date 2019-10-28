package com.example.testfls.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsItem::class], version = 1)
abstract class NewsRoomDatabase : RoomDatabase(){

    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsRoomDatabase? = null

        fun getDatabase(context: Context): NewsRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsRoomDatabase::class.java,
                        "news_database"
                    )//.fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
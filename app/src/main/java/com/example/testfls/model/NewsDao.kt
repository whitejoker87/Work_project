package com.example.testfls.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

import io.reactivex.Single

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(items: List<NewsItem>): Completable

    @Query("SELECT * from news_items")
    fun getNews(): Single<List<NewsItem>>


}
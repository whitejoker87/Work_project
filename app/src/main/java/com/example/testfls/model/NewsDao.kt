package com.example.testfls.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

import io.reactivex.Single

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<NewsItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(item: NewsItem)

    @Query("SELECT * from news_items")
    fun getNews(): Single<List<NewsItem>>

    @Query("SELECT * from news_items WHERE title = :newsTitle")
    fun getNewsItem(newsTitle: String): Single<List<NewsItem>>


}
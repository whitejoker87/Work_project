package com.example.testfls.model

import com.example.testfls.App
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class NewsRepository {

    private var newsDao: NewsDao = App.dao!!
    private val provider = RssProvider()


    fun insertAll(newsItems: List<NewsItem>): Completable {
        return newsDao.insertAll(newsItems)
    }

    fun getRss(): Single<List<NewsItem>> {
        return newsDao.getNews()
            .flatMap { news ->
                return@flatMap if (news.isEmpty()) {
                    Single.fromObservable(getListNews())
                } else {
                    Single.just(news)
                }
            }
    }

    private fun getListNews(): Observable<List<NewsItem>> {
        return provider.getRss()
            .flatMap { rss ->
                Observable.fromArray(rss.channel.items)
                    .doOnNext { news -> insertAll(news) }
            }
    }


}
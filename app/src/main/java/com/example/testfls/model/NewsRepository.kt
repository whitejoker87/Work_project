package com.example.testfls.model

import com.example.testfls.App
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRepository(private val newsDao: NewsDao, private val provider: RssProvider) {

//    private var LOG_TAG = "LOG_TAG"

    fun getListNews(isRefresh: Boolean): Single<List<NewsItem>> {
        return newsDao.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { news ->
                return@flatMap if (news.isEmpty() || isRefresh) {
                    Single.fromObservable(getListNewsFromApi())
                } else {
                    Single.just(news)
                }
            }
    }

    private fun getListNewsFromApi(): Observable<List<NewsItem>> {
        return provider.getRss()
            .flatMap { rss -> putNewsInBase(rss.channel.items) }
    }


    private fun putNewsInBase(newsItems: List<NewsItem>): Observable<List<NewsItem>> {
        return Observable.fromCallable { insertAll(newsItems) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

//    private fun insertOne(items: List<NewsItem>): List<NewsItem> {
//        newsDao.insertOne(items[0])
//        return items
//    }

    private fun insertAll(newsItems: List<NewsItem>): List<NewsItem>  {
        newsDao.insertAll(newsItems)
        return newsItems
    }

    fun getNewsItem(title: String): Single<NewsItem> {
        return newsDao.getNewsItem(title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { newsItems ->
                return@flatMap if (newsItems.isEmpty()) {
                    Single.fromObservable(getNewsItemFromApi(title))
                } else {
                    Single.just(newsItems[0])
                }
            }
    }

    private fun getNewsItemFromApi(title: String): Observable<NewsItem> {
        return provider.getRss()
            .flatMap { rss -> putNewsInBase(rss.channel.items) }
            .flatMap { news ->  getOneItemFromListFromApi(news, title)}
    }

    private fun getOneItemFromListFromApi(news: List<NewsItem>, title: String): Observable<NewsItem> {
        var currentItem: NewsItem? = null
        run loop@{
            news.forEach {
                if (it.title == title) {
                    currentItem = it
                    return@loop
                }
            }
        }

        return Observable.just(currentItem)
    }


//    private fun getError(error: Throwable) {
//        Log.e(LOG_TAG, error.toString())
//    }
}
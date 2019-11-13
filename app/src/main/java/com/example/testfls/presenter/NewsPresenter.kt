package com.example.testfls.presenter

import com.example.testfls.model.*
import com.example.testfls.view.NewsView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val repository: NewsRepository) : MvpBasePresenter<NewsView> () {

    var compositeDisposable = CompositeDisposable()

    fun getRss(pullToRefresh: Boolean) {

        view?.showLoading(pullToRefresh)

        compositeDisposable.add(
            repository
                .getListNews(pullToRefresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .map { news ->  sortNews(news)}
//                .map { news -> changeTypeOfTime(news) }
                .subscribe ({ news: List<NewsItem> -> setRss(news)},{ t: Throwable -> setError(t, pullToRefresh)})
        )
    }

//    private fun sortNews(news: List<NewsItem>): List<NewsItem> =
//        news.sortedWith(compareByDescending { dateTimeStrToLocalDateTime(it.pubDate) })
//
//    private fun changeTypeOfTime(news: List<NewsItem>): List<NewsItem> =
//        news.map { offsetTimeToLocalTime(it) }



    private fun setRss(news: List<NewsItem>) {
        view?.setData(news)
        view?.showContent()
    }

    private fun setError(t: Throwable, pullToRefresh: Boolean) {
        view?.showError(t, pullToRefresh)
    }


    override fun destroy() {
        compositeDisposable.dispose()
        super.destroy()
    }



//    private val dateTimeStrToLocalDateTime: (String) -> OffsetDateTime = {
//        OffsetDateTime.parse(it, DateTimeFormatter.ofPattern("EEE, dd LLL yyyy HH:mm:ss XX"))/*<pubDate>Tue, 12 Nov 2019 12:05:23 +0300</pubDate>*/
//    }

//    private fun offsetTimeToLocalTime (newsItem: NewsItem): NewsItem  {
//        newsItem.pubDate = OffsetDateTime.parse(newsItem.pubDate, DateTimeFormatter.ofPattern("EEE, dd LLL yyyy HH:mm:ss XX"))
//            .toInstant()
//            .atOffset(OffsetDateTime.now().offset)
//            .toLocalDateTime()
//            .format(DateTimeFormatter.ofPattern("EEE, dd LLL yyyy HH:mm:ss"))
//        return newsItem
//    }
}
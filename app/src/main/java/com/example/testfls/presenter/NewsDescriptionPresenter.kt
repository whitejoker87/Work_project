package com.example.testfls.presenter

import com.example.testfls.model.NewsItem
import com.example.testfls.model.NewsRepository
import com.example.testfls.model.Rss
import com.example.testfls.model.RssProvider
import com.example.testfls.view.NewsDescriptionView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsDescriptionPresenter: MvpBasePresenter<NewsDescriptionView>() {
    private val repository = NewsRepository()

    var compositeDisposable = CompositeDisposable()

    fun getNewsItem(pullToRefresh: Boolean, id: Int) {

        view?.showLoading(pullToRefresh)

        compositeDisposable.add(
            repository.getRss().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe ({ news: List<NewsItem> -> setRss(news, id)},{ t: Throwable -> setError(t, pullToRefresh)})
        )
    }

    fun setRss(news: List<NewsItem>, id: Int) {
        view?.setData(news[id])
        view?.showContent()
    }

    fun setError(t: Throwable, pullToRefresh: Boolean) {
        view?.showError(t, pullToRefresh)
    }

    override fun destroy() {
        compositeDisposable.dispose()
        super.destroy()
    }
}
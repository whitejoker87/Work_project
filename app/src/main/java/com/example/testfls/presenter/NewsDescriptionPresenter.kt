package com.example.testfls.presenter

import com.example.testfls.App
import com.example.testfls.model.NewsItem
import com.example.testfls.model.NewsRepository
import com.example.testfls.view.NewsDescriptionView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsDescriptionPresenter @Inject constructor(private val repository: NewsRepository): MvpBasePresenter<NewsDescriptionView>() {

    var compositeDisposable = CompositeDisposable()

    fun getNewsItem(pullToRefresh: Boolean, title: String) {

        view?.showLoading(pullToRefresh)

        compositeDisposable.add(
            repository
                .getNewsItem(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ newsItem: NewsItem -> setRss(newsItem)},{ t: Throwable -> setError(t, pullToRefresh)})
        )
    }

    fun setRss(newsItem: NewsItem) {
        view?.setData(newsItem)
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
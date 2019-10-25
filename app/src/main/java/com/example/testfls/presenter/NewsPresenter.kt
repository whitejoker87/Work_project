package com.example.testfls.presenter

import com.example.testfls.App
import com.example.testfls.model.*
import com.example.testfls.view.NewsView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsPresenter : MvpBasePresenter<NewsView> () {


    var compositeDisposable = CompositeDisposable()

    fun getRss(pullToRefresh: Boolean) {

        view?.showLoading(pullToRefresh)

        compositeDisposable.add(
            App.repository!!.getRss().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe ({ news: List<NewsItem> -> setRss(news)},{ t: Throwable -> setError(t, pullToRefresh)})
        )
    }

    fun setRss( news: List<NewsItem>) {
        view?.setData(news)
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
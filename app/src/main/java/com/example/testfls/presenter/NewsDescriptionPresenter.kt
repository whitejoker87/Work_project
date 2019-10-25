package com.example.testfls.presenter

import com.example.testfls.model.Rss
import com.example.testfls.model.RssProvider
import com.example.testfls.view.NewsDescriptionView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsDescriptionPresenter: MvpBasePresenter<NewsDescriptionView>() {
    private val provider = RssProvider()

    var compositeDisposable = CompositeDisposable()

    fun getNewsItem(pullToRefresh: Boolean, id: Int) {

        view?.showLoading(pullToRefresh)

        compositeDisposable.add(
            provider.getRss().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe ({ rss: Rss -> setRss(rss, id)},{ t: Throwable -> setError(t, pullToRefresh)})
        )
    }

    fun setRss(rss: Rss, id: Int) {
        view?.setData(rss.channel.items[id])
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
package com.example.testfls.presenter

import com.example.testfls.model.Rss
import com.example.testfls.model.RssProvider
import com.example.testfls.view.NewsView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.disposables.CompositeDisposable

class NewsPresenter : MvpBasePresenter<NewsView> () {
    private val provider = RssProvider()

    var compositeDisposable = CompositeDisposable()

    fun getRss(pullToRefresh: Boolean) {

        view?.showLoading(pullToRefresh)

        compositeDisposable.add(
            provider.getRss().subscribe ({rss: Rss -> setRss(rss)},{ t: Throwable -> setError(t, pullToRefresh)})
        )
    }

    fun setRss( rss: Rss) {
        view?.setData(rss.channel.items)
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
package com.example.testfls.presenter

import com.example.testfls.model.NewsItem
import com.example.testfls.model.NewsRepository
import com.example.testfls.view.NewsDescriptionView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsDescriptionPresenter /*@Inject constructor*/(private val repository: NewsRepository, private val title: String?): MvpBasePresenter<NewsDescriptionView>() {

//    @AssistedInject.Factory
//    interface Factory {
//        fun create(title: String?): NewsDescriptionPresenter
//    }

    lateinit var item: NewsItem
        private set

    var compositeDisposable = CompositeDisposable()

    fun getNewsItem(pullToRefresh: Boolean) {

        view?.showLoading(pullToRefresh)

        compositeDisposable.add(
            repository
                .getNewsItem(title?:"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ newsItem: NewsItem -> setRss(newsItem)},{ t: Throwable -> setError(t, pullToRefresh)})
        )
    }

    fun setRss(newsItem: NewsItem) {
        item = newsItem
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
//package com.example.testfls.presenter
//
//import com.example.testfls.model.*
//import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.schedulers.Schedulers
//import javax.inject.Inject
//
//class NewsPresenter @Inject constructor(private val repository: NewsRepository) : MvpBasePresenter<NewsView> () {
//
//    var compositeDisposable = CompositeDisposable()
//
//    fun getRss(pullToRefresh: Boolean) {
//
//        view?.showLoading(pullToRefresh)
//
//        compositeDisposable.add(
//            repository
//                .getListNews(pullToRefresh)
//                .map { news -> sortNews(news) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe ({ news: List<NewsItem> -> setNewsItem(news)},{ t: Throwable -> setError(t, pullToRefresh)})
//        )
//    }
//
//    private fun setNewsItem(news: List<NewsItem>) {
//        view?.setData(news)
//        view?.showContent()
//    }
//
//    private fun setError(t: Throwable, pullToRefresh: Boolean) {
//        view?.showError(t, pullToRefresh)
//    }
//
//
//    override fun destroy() {
//        compositeDisposable.dispose()
//        super.destroy()
//    }
//
//
//    private fun sortNews(news: List<NewsItem>): List<NewsItem> =
//        news.sortedWith(compareByDescending { it.pubDate })
//
//}
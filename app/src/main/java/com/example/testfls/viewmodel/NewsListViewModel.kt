package com.example.testfls.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testfls.model.NewsItem
import com.example.testfls.model.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {


    var compositeDisposable = CompositeDisposable()

    private val isLoading = MutableLiveData<Boolean>(false)
    private val isRefresh = MutableLiveData<Boolean>(false)


    private val listNews: MutableLiveData<List<NewsItem>> by lazy {
        listNews.also {
            getRss()
        }
    }


    fun setRefresh(refresh: Boolean) {
        isRefresh.value = refresh
    }

    fun isRefresh(): LiveData<Boolean> = isRefresh

    fun isLoading(): LiveData<Boolean> = isLoading

    fun getListNews(): LiveData<List<NewsItem>> = listNews


    fun getRss() {

        isLoading.value = true

        compositeDisposable.add(
            repository
                .getListNews(isRefresh().value as Boolean)
                .map { news -> sortNews(news) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ news: List<NewsItem> -> setRss(news) },
                    { t: Throwable -> setError(t, isRefresh().value as Boolean) })
        )
    }

    private fun setRss(news: List<NewsItem>) {
        listNews.value = news
        isLoading.value = false
    }

    private fun sortNews(news: List<NewsItem>): List<NewsItem> =
        news.sortedWith(compareByDescending { it.pubDate })

    private fun setError(t: Throwable, pullToRefresh: Boolean) {
//        view?.showError(t, pullToRefresh)
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}












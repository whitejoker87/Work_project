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


    private var compositeDisposable = CompositeDisposable()

    private val isLoading = MutableLiveData<Boolean>(false)
    private val isRefresh = MutableLiveData<Boolean>(false)
    private val error = MutableLiveData<Throwable>()

    private val listNews = MutableLiveData<List<NewsItem>>()


    fun setRefresh(refresh: Boolean) {
        isRefresh.value = refresh
    }

    fun isRefresh() = isRefresh

    fun isLoading() = isLoading

    private fun setError(t: Throwable) {
        isLoading.value = false
        error.value = t
    }

    fun getError() = error


    private fun setNews(news: List<NewsItem>) {
        listNews.value = news
        isLoading.value = false
    }

    fun getListNews(): LiveData<List<NewsItem>> = listNews


    fun getRss() {

        isLoading.value = true

        compositeDisposable.add(
            repository
                .getListNews(isRefresh().value as Boolean)
                .map { news -> sortNews(news) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ news: List<NewsItem> -> setNews(news) },
                    { t: Throwable -> setError(t) })
        )
    }


    private fun sortNews(news: List<NewsItem>): List<NewsItem> =
        news.sortedWith(compareByDescending { it.pubDate })


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}












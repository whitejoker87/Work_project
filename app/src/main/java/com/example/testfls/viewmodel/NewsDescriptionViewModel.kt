package com.example.testfls.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testfls.model.NewsItem
import com.example.testfls.model.NewsRepository
import com.example.testfls.view.NewsDescriptionFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsDescriptionViewModel @Inject constructor(private val repository: NewsRepository, private val title: String): ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    private val isLoading = MutableLiveData<Boolean>(false)

    private val newsItem = MutableLiveData<NewsItem>(NewsItem("", 0, "", ""))


    fun isLoading(): LiveData<Boolean> = isLoading

    private fun setNewsItem(item: NewsItem) {
        newsItem.value = item
    }

    fun getNewsItem(): LiveData<NewsItem> = newsItem

    fun sendNewsItem() {

        isLoading.value = true

        compositeDisposable.add(
            repository
                .getNewsItem(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ newsItem: NewsItem -> fillNewsItem(newsItem)},{ t: Throwable -> setError(t)})
        )
    }

    private fun fillNewsItem(newsItem: NewsItem) {
        setNewsItem(newsItem)
        isLoading.value = false
    }

    private fun setError(t: Throwable) {
//        view?.showError(t, pullToRefresh)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
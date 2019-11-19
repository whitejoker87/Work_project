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
import javax.inject.Named

class NewsDescriptionViewModel @Inject constructor(private val repository: NewsRepository, @Named("Title") private val title: String): ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    private val isLoading = MutableLiveData<Boolean>(false)

    private val error = MutableLiveData<Throwable>()

    private val newsItem = MutableLiveData<NewsItem>(NewsItem("", 0, "", ""))


    fun isLoading(): LiveData<Boolean> = isLoading

    private fun setError(t: Throwable) {
        isLoading.value = false
        error.value = t
    }

    fun getError(): LiveData<Throwable> = error

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


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
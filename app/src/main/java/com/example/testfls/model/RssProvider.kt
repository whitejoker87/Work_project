package com.example.testfls.model

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RssProvider(private var api: RssApi) {

    fun getRss(): Observable<Rss> =
         api.getAllRss().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

}
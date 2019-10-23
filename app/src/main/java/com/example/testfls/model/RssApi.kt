package com.example.testfls.model

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RssApi {
    @GET("rss/all")
    fun getAllRss(): Observable<Rss>
}
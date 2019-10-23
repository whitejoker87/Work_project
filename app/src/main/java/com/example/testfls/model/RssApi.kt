package com.example.testfls.model

import retrofit2.Call
import retrofit2.http.GET

interface RssApi {
    @GET("rss/all")
    fun getAllRss(): Call<Rss>
}
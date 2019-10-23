package com.example.testfls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testfls.model.Rss
import com.example.testfls.model.RssApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getRss()


    }

    internal fun getRss() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .build()
        val api = retrofit.create(RssApi::class.java)
        api.getAllRss().enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {

            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                if (response.body() != null) {
                    setFragment(NewsListFragment(), "list")
                    response.body()!!.channel.items
                }
            }

        })
    }

    private fun setFragment(fragment: Fragment, name: String) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(name).commit()
    }

    companion object {
        var BaseUrl = "https://vc.ru/"
    }
}

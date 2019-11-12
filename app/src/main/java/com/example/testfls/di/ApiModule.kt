package com.example.testfls.di

import com.example.testfls.model.RssApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    private val baseUrl = "https://vc.ru/"

    @Provides
    @Singleton
    internal fun provideRetrofit(converterFactory: Converter.Factory, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideConverterFactory(): Converter.Factory = SimpleXmlConverterFactory.create()

    @Provides
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    internal fun provideRssApi(retrofit: Retrofit): RssApi = retrofit.create(RssApi::class.java)

}

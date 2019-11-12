package com.example.testfls.di

import com.example.testfls.model.NewsRepository
import com.example.testfls.presenter.NewsDescriptionPresenter
import com.example.testfls.view.NewsDescriptionFragment
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {


    @Provides
    fun provideNewsDescriptionPresenter(repository: NewsRepository, title: String): NewsDescriptionPresenter =
        NewsDescriptionPresenter(repository, title)

}
package com.example.testfls.di

import com.example.testfls.view.ARG_TITLE
import com.example.testfls.view.NewsDescriptionFragment
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class NewsDescriptionFragmentModule {

    @Provides
    @Named(ARG_TITLE)
    fun provideTitle(newsDescriptionFragment: NewsDescriptionFragment): String {
        var title: String? = ""
        newsDescriptionFragment.arguments?.let {
            title = it.getString(ARG_TITLE)
        }
        return title!!
    }
}
package com.example.testfls.di

import com.example.testfls.view.NewsDescriptionFragment
import dagger.Module
import dagger.Provides

@Module
class NewsDescriptionFragmentModule {

    @Provides
    fun provideTitle(newsDescriptionFragment: NewsDescriptionFragment): String {
        val arg = "title"
        var title: String? = ""
        newsDescriptionFragment.arguments?.let {
            title = it.getString(arg)
        }
        return title!!
    }
}
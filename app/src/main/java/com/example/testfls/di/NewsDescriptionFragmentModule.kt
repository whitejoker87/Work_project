package com.example.testfls.di

import com.example.testfls.view.NewsDescriptionFragment
import com.example.testfls.view.NewsListFragment
import dagger.Module
import dagger.Provides

@Module
class NewsDescriptionFragmentModule: FragmentModule() {
    override fun contributeNewsListFragment(): NewsListFragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun contributeNewsDescriptionFragment(): NewsDescriptionFragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Provides
    fun provideTitle(newsDescriptionFragment: NewsDescriptionFragment): String? {
        val arg = "title"
        var title: String? = ""
        newsDescriptionFragment.arguments?.let {
            title = it.getString(arg)
        }
        return title
    }
}
package com.example.testfls.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.testfls.model.NewsRepository
//import com.example.testfls.view.ARG_TITLE
import com.example.testfls.view.NewsDescriptionFragment
import com.example.testfls.viewmodel.NewsDescriptionViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class NewsDescriptionFragmentModule {

    @Provides
    @Named("Title")
    fun provideTitle(newsDescriptionFragment: NewsDescriptionFragment): String {
        var title: String? = ""
//        newsDescriptionFragment.arguments?.let {
//            title = it.getString(ARG_TITLE)
//        }
        newsDescriptionFragment.mainViewModel.getTitleDescription().value?.let {
            title = it
        }
        return title!!
    }
}
package com.example.testfls.di

import com.example.testfls.view.NewsDescriptionFragment
import com.example.testfls.view.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {


    @ContributesAndroidInjector
    abstract fun contributeNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsDescriptionFragment(): NewsDescriptionFragment

}
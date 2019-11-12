package com.example.testfls.di

import com.example.testfls.view.NewsDescriptionFragment
import com.example.testfls.view.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ContributesAndroidInjector
    fun contributeNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector(modules = [NewsDescriptionFragmentModule::class])
    fun contributeNewsDescriptionFragment(): NewsDescriptionFragment

}
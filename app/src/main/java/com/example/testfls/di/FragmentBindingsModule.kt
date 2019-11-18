package com.example.testfls.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.testfls.model.NewsRepository
import com.example.testfls.view.NewsDescriptionFragment
import com.example.testfls.view.NewsListFragment
import com.example.testfls.viewmodel.NewsDescriptionViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface FragmentBindingsModule {

    @ContributesAndroidInjector
    fun contributeNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector(modules = [NewsDescriptionFragmentModule::class])
    fun contributeNewsDescriptionFragment(): NewsDescriptionFragment
}
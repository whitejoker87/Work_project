package com.example.testfls.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testfls.model.NewsRepository
//import com.example.testfls.viewmodel.MainViewModel
import com.example.testfls.viewmodel.NewsDescriptionViewModel
import com.example.testfls.viewmodel.NewsListViewModel
import com.example.testfls.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory): ViewModelProvider.Factory


//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    internal abstract fun newsListViewModel(viewModel: NewsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDescriptionViewModel::class)
    internal abstract fun newsDescriptionViewModel(viewModel: NewsDescriptionViewModel): ViewModel
}
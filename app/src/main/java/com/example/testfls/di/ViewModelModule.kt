//package com.example.testfls.di
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.testfls.viewmodel.MainViewModel
//import com.example.testfls.viewmodel.NewsDescriptionViewModel
//import com.example.testfls.viewmodel.NewsListViewModel
//import com.example.testfls.viewmodel.ViewModelFactory
//import dagger.Binds
//import dagger.Module
//import dagger.multibindings.IntoMap
//
//@Module
//abstract class ViewModelModule {
//
//    @Binds
//    abstract fun bindViewModelFactory(
//        factory: ViewModelFactory<ViewModel>): ViewModelProvider.Factory
//
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(NewsListViewModel::class)
//    abstract fun newsListViewModel(viewModel: NewsListViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(NewsDescriptionViewModel::class)
//    abstract fun newsDescriptionViewModel(viewModel: NewsDescriptionViewModel): ViewModel
//}
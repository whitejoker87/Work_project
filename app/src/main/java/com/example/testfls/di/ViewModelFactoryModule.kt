//package com.example.testfls.di
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import dagger.Module
//import dagger.Provides
//import javax.inject.Provider
//import javax.inject.Singleton
//
//@Module
//class ViewModelFactoryModule {
//
//    @Provides
//    @Singleton
//    fun provideViewModelFactory(
//        providers:  MutableMap<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
//    ) = object : ViewModelProvider.Factory {
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            return requireNotNull(providers[modelClass as Class<out ViewModel>]).get() as T
//        }
//    }
//
//}

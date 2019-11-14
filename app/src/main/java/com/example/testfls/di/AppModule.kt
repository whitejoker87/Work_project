package com.example.testfls.di

import com.example.testfls.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [
    AndroidSupportInjectionModule::class,
//        AndroidInjectionModule::class,
    RepositoryModule::class,
    ApiModule::class,
    DbModule::class,
    ViewModelModule::class
])
interface AppModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun  contributeMainActivity(): MainActivity

}
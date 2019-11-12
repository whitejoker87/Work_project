package com.example.testfls.di

import com.example.testfls.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface AppModule {

    @ContributesAndroidInjector(modules = [M::class])
    fun  contributeMainActivity(): MainActivity

}
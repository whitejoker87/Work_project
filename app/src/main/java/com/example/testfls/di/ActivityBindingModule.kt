package com.example.testfls.di

import com.example.testfls.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

    @ContributesAndroidInjector(modules = [FragmentBindingsModule::class])
    fun contributeMainActivity(): MainActivity

}
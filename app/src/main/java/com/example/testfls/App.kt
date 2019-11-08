package com.example.testfls

import android.app.Application
import com.example.testfls.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    @Inject lateinit var dispatchingAndroidInjector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any>? = dispatchingAndroidInjector


    override fun onCreate() {
        super.onCreate()

       DaggerAppComponent.builder()
           .application(this)
           .build()
           .inject(this)


   }
//    companion object {
//
//        var appComponent: AppComponent? = null
//            private set
//    }
}
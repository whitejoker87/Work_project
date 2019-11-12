package com.example.testfls

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.testfls.di.app.AppComponent
import com.example.testfls.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    @Inject lateinit var dispatchingAndroidInjector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any>? = dispatchingAndroidInjector

//    lateinit var component: AppComponent


    override fun onCreate() {
        super.onCreate()

       DaggerAppComponent.builder()
           .application(this)
           .build()
           .inject(this)



   }

}

//val Fragment.component get() = (activity?.application as App).component
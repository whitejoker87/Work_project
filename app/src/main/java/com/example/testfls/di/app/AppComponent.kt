package com.example.testfls.di.app

import android.app.Application
import com.example.testfls.App
import com.example.testfls.di.*
import com.example.testfls.presenter.NewsDescriptionPresenter
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

//    val newsDescriptionPresenterFactory: NewsDescriptionPresenter.Factory
}
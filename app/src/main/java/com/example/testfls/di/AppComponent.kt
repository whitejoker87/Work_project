package com.example.testfls.di

import android.app.Application
import com.example.testfls.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
//        AndroidInjectionModule::class,
        RepositoryModule::class,
        ApiModule::class,
        DbModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
//    fun getApi(): RssApi
//    fun inject(newsListFragment: NewsListFragment)
//    fun inject(newsDescriptionFragment: NewsDescriptionFragment)

    fun inject(app: App)
}
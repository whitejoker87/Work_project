package com.example.testfls.di

import android.app.Application
import com.example.testfls.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,

        ActivityBindingModule::class,
//        FragmentBindingsModule::class,
//        NewsDescriptionFragmentModule::class,

        RepositoryModule::class,
        ApiModule::class,
        DbModule::class
//        ViewModelFactoryModule::class
//        ViewModelModule::class
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

}
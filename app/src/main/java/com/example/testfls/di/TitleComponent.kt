//package com.example.testfls.di
//
//import dagger.BindsInstance
//import dagger.Component
//import dagger.Subcomponent
//
//@Subcomponent(modules = [TitleModule::class])
//interface TitleComponent {
//    fun getTitleComponent(): TitleComponent
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun setTitle(@Title title: String)
//
//        fun build(): TitleComponent
//    }
//}
package com.example.testfls.di

import com.example.testfls.model.NewsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {
    fun getRepository(): NewsRepository
}
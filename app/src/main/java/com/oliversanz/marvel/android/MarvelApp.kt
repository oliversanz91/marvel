package com.oliversanz.marvel.android

import android.app.Application
import com.oliversanz.marvel.di.mapperModule
import com.oliversanz.marvel.di.repositoryModule
import com.oliversanz.marvel.di.useCaseModule
import com.oliversanz.marvel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MarvelApp)
            modules(listOf(repositoryModule, viewModelModule, useCaseModule, mapperModule))
        }
    }

}
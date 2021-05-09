package com.oliversanz.marvel.di

import com.oliversanz.marvel.data.mapper.ToHeroDetailMapper
import com.oliversanz.marvel.data.mapper.ToHeroListMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { ToHeroListMapper() }
    factory { ToHeroDetailMapper() }
}
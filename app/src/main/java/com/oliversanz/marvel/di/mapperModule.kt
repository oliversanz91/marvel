package com.oliversanz.marvel.di

import com.oliversanz.marvel.data.mapper.HeroListMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { HeroListMapper() }
}
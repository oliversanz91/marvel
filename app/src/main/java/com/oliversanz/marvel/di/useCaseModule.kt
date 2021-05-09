package com.oliversanz.marvel.di

import com.oliversanz.marvel.domain.usecases.GetHeroDetailUseCase
import com.oliversanz.marvel.domain.usecases.GetHeroListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetHeroListUseCase(repository = get(), mapper = get()) }
    factory { GetHeroDetailUseCase(repository = get(), mapper = get()) }
}
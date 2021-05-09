package com.oliversanz.marvel.di

import com.oliversanz.marvel.domain.usecases.GetHeroListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetHeroListUseCase(repository = get(), mapper = get()) }
}
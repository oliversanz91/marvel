package com.oliversanz.marvel.di

import com.oliversanz.marvel.android.herolist.HeroListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HeroListViewModel(getHeroListUseCase = get()) }
}
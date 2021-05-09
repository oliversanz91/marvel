package com.oliversanz.marvel.di

import android.content.Context
import com.oliversanz.marvel.R
import com.oliversanz.marvel.data.network.config.AuthorizedHttpClient
import com.oliversanz.marvel.data.network.repository.HeroApi
import com.oliversanz.marvel.data.network.repository.HeroRepository
import com.oliversanz.marvel.data.network.repository.HeroRepositoryImpl
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {


    single<OkHttpClient>(named("authorized-client")) {
        AuthorizedHttpClient(
            publicKey = get<Context>().getString(R.string.MARVEL_API_KEY),
            privateKey = get<Context>().getString(R.string.MARVEL_PRIVATE_KEY)
        ).getClient()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com")
            .client(get(named("authorized-client")))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    single<HeroApi> {
        get<Retrofit>().create(HeroApi::class.java)
    }

    single<HeroRepository> {
        HeroRepositoryImpl(heroApi = get(), dispatcher = Dispatchers.IO)
    }


}
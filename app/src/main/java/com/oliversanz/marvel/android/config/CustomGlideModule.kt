package com.oliversanz.marvel.android.config

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import java.io.InputStream

@GlideModule
class CustomGlideModule : AppGlideModule(), KoinComponent {

    private val authorizedHttpClient: OkHttpClient by inject(named("authorized-client"))

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val factory = OkHttpUrlLoader.Factory(authorizedHttpClient)
        glide.registry.replace(GlideUrl::class.java, InputStream::class.java, factory)
    }

}
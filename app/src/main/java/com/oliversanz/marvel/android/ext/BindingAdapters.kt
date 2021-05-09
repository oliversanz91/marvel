package com.oliversanz.marvel.android.ext

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.oliversanz.marvel.android.config.GlideApp


@BindingAdapter("android:url")
fun loadImage(imageView: ImageView, url: String?) {
    Log.d("--->", "Cargamos $url")
    if (url != null) {
        GlideApp.with(imageView)
            .load(url)
            .fitCenter()
            .into(imageView)
    }
}
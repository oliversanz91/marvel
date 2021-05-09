package com.oliversanz.marvel.android.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.oliversanz.marvel.android.config.GlideApp


@BindingAdapter("android:url")
fun loadImage(imageView: ImageView, url: String) {
    GlideApp.with(imageView)
        .load(url)
        .centerCrop()
        .into(imageView)
}
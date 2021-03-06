package com.oliversanz.marvel.android.ext

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.oliversanz.marvel.android.config.GlideApp


@BindingAdapter("android:url")
fun loadImage(imageView: ImageView, url: String?) {
    if (url != null) {
        GlideApp.with(imageView)
            .load(url)
            .fitCenter()
            .into(imageView)
    }
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, boolean: Boolean) {
    view.visibility =
        if (boolean) View.VISIBLE
        else View.GONE
}
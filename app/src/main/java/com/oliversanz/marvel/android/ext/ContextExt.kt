package com.oliversanz.marvel.android.ext

import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.openUrl(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    this.startActivity(browserIntent)
}
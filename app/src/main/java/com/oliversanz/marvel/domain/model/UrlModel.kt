package com.oliversanz.marvel.domain.model

import java.util.*

data class UrlModel(
    val type: String,
    val url: String
) {
    val id = UUID.randomUUID().toString()
}

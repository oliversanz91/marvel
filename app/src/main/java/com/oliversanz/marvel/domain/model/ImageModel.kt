package com.oliversanz.marvel.domain.model

data class ImageModel(
    val path: String,
    private val extension: String
) {

    val thumbnailUrl = "$path/standard_medium.$extension"
    val detailUrl = "$path/landscape_xlarge.$extension"
    val originalUrl = "$path.$extension"

}


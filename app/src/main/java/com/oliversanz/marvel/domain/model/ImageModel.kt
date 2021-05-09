package com.oliversanz.marvel.domain.model

import com.oliversanz.marvel.data.network.entity.Thumbnail

data class ImageModel(
    val path: String,
    private val extension: String
) {

    constructor(thumbnail: Thumbnail?) : this(thumbnail?.path.orEmpty(), thumbnail?.extension.orEmpty())

    val thumbnailUrl = "$path/$THUMBNAIL.$extension"
    val detailUrl = "$path/$DETAIL.$extension"
    val originalUrl = "$path.$extension"

}

private const val THUMBNAIL = "standard_medium"
private const val DETAIL = "portrait_xlarge"


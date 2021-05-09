package com.oliversanz.marvel.data.network.entity


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: String?,
    @SerializedName("limit")
    val limit: String?,
    @SerializedName("offset")
    val offset: String?,
    @SerializedName("results")
    val heroList: List<HeroListItem>?,
    @SerializedName("total")
    val total: String?
)
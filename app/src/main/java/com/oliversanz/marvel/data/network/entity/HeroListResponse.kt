package com.oliversanz.marvel.data.network.entity


import com.google.gson.annotations.SerializedName

data class HeroListResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String?,
    @SerializedName("attributionText")
    val attributionText: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("data")
    val responseData: Data?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("status")
    val status: String?
)
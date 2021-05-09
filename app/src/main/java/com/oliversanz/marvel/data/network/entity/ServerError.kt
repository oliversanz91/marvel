package com.oliversanz.marvel.data.network.entity


import com.google.gson.annotations.SerializedName

data class ServerError(
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String
)
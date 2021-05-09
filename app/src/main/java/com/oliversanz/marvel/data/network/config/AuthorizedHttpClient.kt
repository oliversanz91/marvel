package com.oliversanz.marvel.data.network.config

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.math.BigInteger
import java.security.MessageDigest

class AuthorizedHttpClient(
    private val publicKey: String,
    private val privateKey: String
) {


    fun getClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addNetworkInterceptor(getInterceptor())
        return builder.build()
    }

    private fun getInterceptor(): Interceptor {
        return Interceptor { chain ->

            val timeStamp = System.currentTimeMillis().toString()
            val hash = md5(timeStamp)

            var request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter(PARAM_TS, timeStamp)
                .addQueryParameter(PARAM_API_KEY, publicKey)
                .addQueryParameter(PARAM_HASH, hash)
                .build()

            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }


    private fun md5(timeStamp: String): String {
        val input = (timeStamp+privateKey+publicKey).toByteArray()
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input)).toString(16).padStart(32, '0')
    }

    private companion object {
        private const val PARAM_TS = "ts"
        private const val PARAM_API_KEY = "apikey"
        private const val PARAM_HASH = "hash"
    }
}
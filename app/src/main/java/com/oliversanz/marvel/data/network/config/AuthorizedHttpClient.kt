package com.oliversanz.marvel.data.network.config

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.oliversanz.marvel.data.network.entity.ServerError
import com.oliversanz.marvel.data.network.exceptions.ShowMessageException
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.lang.Exception
import java.math.BigInteger
import java.security.MessageDigest

class AuthorizedHttpClient(
    private val publicKey: String,
    private val privateKey: String
) {

    private val gson: Gson = Gson()

    fun getClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addNetworkInterceptor(getAuthInterceptor())
        builder.addInterceptor(getErrorInterceptor())
        return builder.build()
    }

    private fun getAuthInterceptor(): Interceptor {
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

    private fun getErrorInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)

            if (response.code != 200) {
                val body = response.body?.string()
                Log.e("--->", "[${response.code}] $body")
                val error =
                    try { body?.let { gson.fromJson(it, ServerError::class.java) } }
                    catch (e: Exception) { null }

                throw if (error != null) {
                    ShowMessageException(error.code, error.message)
                }
                else {
                    ShowMessageException()
                }
            }

            response
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
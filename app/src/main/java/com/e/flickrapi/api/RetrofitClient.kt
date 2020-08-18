package com.e.flickrapi.api

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {
        var CONNECTION_TIMEOUT = 30

        private const val BASE_URL =
            "https://www.flickr.com"


        fun create(): RetrofitInterface {
            val httpClient = OkHttpClient.Builder()

            httpClient.readTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            httpClient.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            httpClient.writeTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            val okHttpClient = httpClient.build()


            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(RetrofitInterface::class.java)
        }


    }
}
package com.cred.sampleapp.data.repository.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class APIClient {

    companion object {

        fun getClient(): APIService {
            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // connection timeout
                .readTimeout(30, TimeUnit.SECONDS) // read timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.mocklets.com/p6764/test_mint/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(APIService::class.java)
        }
    }
}
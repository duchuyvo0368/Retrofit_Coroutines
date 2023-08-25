package com.example.retrofit_coroutines.data.remote

import com.example.retrofit_coroutines.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.addHeaderLenient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteData {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient = okHttpBuilder.addInterceptor(loggingInterceptor)

    private fun getUser(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }
    val apiService= getUser().create(ApiService::class.java)
}
package com.example.retrofit_coroutines.data.remote

import com.example.retrofit_coroutines.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers():List<User>
}
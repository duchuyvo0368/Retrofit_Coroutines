package com.example.retrofit_coroutines.data.remote

import com.example.retrofit_coroutines.data.model.User

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers(): List<User> = apiService.getUsers()
}
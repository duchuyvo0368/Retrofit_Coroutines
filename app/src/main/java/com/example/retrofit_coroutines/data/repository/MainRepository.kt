package com.example.retrofit_coroutines.data.repository

import com.example.retrofit_coroutines.data.remote.ApiHelper

class MainRepository (private val apiHelper: ApiHelper){

    suspend fun getUsers()=apiHelper.getUsers()
}
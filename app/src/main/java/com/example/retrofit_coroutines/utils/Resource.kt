package com.example.retrofit_coroutines.utils

open class Resource<out R>{
    data class Success<out T>(val data: T? = null, val msg: String? = null) : Resource<T>()
    class Loading:Resource<Nothing>()
    class DataError<T>(message: String):Resource<T>()
}
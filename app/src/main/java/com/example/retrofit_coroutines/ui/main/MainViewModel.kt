package com.example.retrofit_coroutines.ui.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.retrofit_coroutines.data.remote.ApiHelper
import com.example.retrofit_coroutines.data.repository.MainRepository
import com.example.retrofit_coroutines.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(MainRepository(apiHelper)) as T
    }
}

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.Success(null))
        try {
            emit(Resource.Success(mainRepository.getUsers()))
        }catch (e:Exception){
            emit(Resource.DataError(e.message!!))
        }
    }
}
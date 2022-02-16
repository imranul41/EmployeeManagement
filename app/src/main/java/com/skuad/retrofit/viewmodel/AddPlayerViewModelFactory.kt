package com.skuad.retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skuad.retrofit.remote.ApiInterface

class AddPlayerViewModelFactory(private val apiInterface: ApiInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddPlayerViewModel(apiInterface) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
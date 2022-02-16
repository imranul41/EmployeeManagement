package com.skuad.retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skuad.retrofit.remote.ApiInterface

class AddEmployeeViewModelFactory(private val apiInterface: ApiInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEmployeeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddEmployeeViewModel(apiInterface) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
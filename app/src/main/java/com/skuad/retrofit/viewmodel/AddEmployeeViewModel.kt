package com.skuad.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.remote.ApiInterface
import kotlinx.coroutines.launch
import retrofit2.Response

class AddEmployeeViewModel(private val apiInterface: ApiInterface) : ViewModel() {

    val employeeLiveData = MutableLiveData<Response<Employee>>()

    fun addEmployee(employee: Employee) {
        viewModelScope.launch {
            employeeLiveData.value = apiInterface.addEmployee(employee)
        }
    }

}
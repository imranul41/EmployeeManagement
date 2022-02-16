package com.skuad.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.remote.ApiInterface
import kotlinx.coroutines.launch

class ShowEmployeeViewModel(private val apiInterface: ApiInterface) : ViewModel() {
    val employeeLiveData = MutableLiveData<List<Employee>>()


    fun getAllEmployee() {
        viewModelScope.launch {
            employeeLiveData.value = apiInterface.getAllEmployee()
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch {
           val response = apiInterface.deleteEmployee(employee.id)
            if(response.isSuccessful){
                employeeLiveData.value = apiInterface.getAllEmployee()
            }
        }
    }
}
package com.skuad.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.remote.ApiInterface
import kotlinx.coroutines.launch

class UpdateEmployeeViewModel(private val apiInterface: ApiInterface) : ViewModel() {

    val employeeLiveData = MutableLiveData<Employee?>()

    val updateLiveData = MutableLiveData<Boolean>()

    fun getEmployee(id: String) {
        viewModelScope.launch {
            val response = apiInterface.getEmployee(id.toInt())
            if(response.isSuccessful){
                employeeLiveData.value = response.body()
            }
        }
    }

    fun updateEmployee(employee: Employee) {
        viewModelScope.launch {
            val response = apiInterface.updateEmployee(id = employee.id, employee = employee)
            updateLiveData.value = response.isSuccessful
        }
    }

}
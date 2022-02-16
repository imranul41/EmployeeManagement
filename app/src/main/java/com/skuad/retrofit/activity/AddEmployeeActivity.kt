package com.skuad.retrofit.activity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.skuad.retrofit.databinding.ActivityAddEmployeeBinding
import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.remote.RetrofitClient
import com.skuad.retrofit.viewmodel.AddEmployeeViewModel
import com.skuad.retrofit.viewmodel.AddEmployeeViewModelFactory

class AddEmployeeActivity : AppCompatActivity() {

        lateinit var binding: ActivityAddEmployeeBinding
        private val designationList = listOf("Select Designation", "Software Engineer", "Lead Engineer", "Manager")
        private val departmentList = listOf("Select Department", "IT", "Marketing", "HR")

    private val viewModel: AddEmployeeViewModel by viewModels(){
        AddEmployeeViewModelFactory(RetrofitClient.apiInterface)
    }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityAddEmployeeBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val designationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, designationList)
            binding.spnDesignation.adapter = designationAdapter

            val departmentList = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, departmentList)
            binding.spnDepartment.adapter = departmentList

            binding.btnSave.setOnClickListener {
                getData()
            }

            observeLiveData()
        }

    private fun observeLiveData() {
        viewModel.employeeLiveData.observe(this, { employee ->
            binding.progress.visibility = View.GONE
            resetData()
            showToast("Employee added successfully")
        })
    }

    private fun resetData() {
        with(binding){
            etFirstName.setText("")
            etLastName.setText("")
            etAddress.setText("")
            spnDesignation.setSelection(0)
            spnDepartment.setSelection(0)
        }
    }

    private fun getData() {

        // First Name

        val firstName = binding.etFirstName.text.toString().trim()

        if (firstName.isEmpty()) {
            showToast("Please enter firstName")
            return
        }

        // Last Name

        val lastName = binding.etLastName.text.toString().trim()

        if (lastName.isEmpty()) {
            showToast("Please enter lastName")
            return
        }
        // Designation
        val designation = binding.spnDesignation.selectedItem as String
        if (designation == "Select Designation") {
            showToast("Please select designation")
            return
        }

        // Department
        val department = binding.spnDepartment.selectedItem as String
        if (department =="Select Department") {
            showToast("Please enter department")
            return
        }
        // Address
        val address = binding.etAddress.text.toString().trim()
        if (address.isEmpty()) {
            showToast("Please enter address")
            return

        }

        val employee = Employee(
            firstName = firstName,
            lastName = lastName,
            designation = designation,
            department = department,
            address = address
        )
        viewModel.addEmployee(employee)
        binding.progress.visibility = View.VISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}




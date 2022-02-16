package com.skuad.retrofit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.skuad.employeemanagement.extension.hideKeyboard
import com.skuad.retrofit.R
import com.skuad.retrofit.databinding.ActivityUpdateEmployeeBinding
import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.remote.RetrofitClient
import com.skuad.retrofit.viewmodel.ShowEmployeeViewModel
import com.skuad.retrofit.viewmodel.ShowEmployeeViewModelFactory
import com.skuad.retrofit.viewmodel.UpdateEmployeeViewModel
import com.skuad.retrofit.viewmodel.UpdateEmployeeViewModelFactory

class UpdateEmployeeActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateEmployeeBinding

    private val designationList =
        listOf("Select Designation", "Software Engineer", "Lead Engineer", "Manager")
    private val departmentList = listOf("Select Department", "IT", "Marketing", "HR")

    private val viewModel: UpdateEmployeeViewModel by viewModels() {
        UpdateEmployeeViewModelFactory(RetrofitClient.apiInterface)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(binding.root)
        setupSpinner()
        addClickEvent()
        observeLiveData()

    }

    private fun setupSpinner() {
        val designationAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, designationList)
        binding.spnDesignation.adapter = designationAdapter

        val departmentAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, departmentList)
        binding.spnDepartment.adapter = departmentAdapter
    }

    private fun observeLiveData() {
        viewModel.employeeLiveData.observe(this, { employee ->
            employee?.let {
                showEmployee(it)
            }
        })

        viewModel.updateLiveData.observe(this, {
            if (it == true) {
                showToast("Updated successfully")
            } else {
                showToast("Update failed")
            }
        })
    }


    private fun showEmployee(employee: Employee) {
        with(binding) {

            // First Name
            etFirstName.setText(employee.firstName)

            // Last Name
            etLastName.setText(employee.lastName)

            // Set Designation
            val indexOfDesignation = designationList.indexOf(employee.designation)
            spnDesignation.setSelection(indexOfDesignation)

            // Set Department
            val indexOfDepartment = departmentList.indexOf(employee.department)
            spnDepartment.setSelection(indexOfDepartment)

            // Address
            etAddress.setText(employee.address)

        }
    }

    private fun addClickEvent() {
        binding.btnSearch.setOnClickListener {
            binding.btnSearch.hideKeyboard()
            val employeeId = binding.etEmployeeId.text.toString().trim()
            if (employeeId.isEmpty()) {
                Toast.makeText(this, "Employee id is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.getEmployee(employeeId)
        }

        binding.btnUpdate.setOnClickListener {
            updateData()
        }
    }


    private fun updateData() {

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
        if (department == "Select Department") {
            showToast("Please enter department")
            return
        }

        // Address

        val address = binding.etAddress.text.toString().trim()

        if (firstName.isEmpty()) {
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


        val employeeId = binding.etEmployeeId.text.toString().trim()
        if (employeeId.isEmpty()) {
            Toast.makeText(this, "Employee id is required", Toast.LENGTH_SHORT).show()
            return
        }

        employee.id = employeeId.toInt()
        viewModel.updateEmployee(employee)
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

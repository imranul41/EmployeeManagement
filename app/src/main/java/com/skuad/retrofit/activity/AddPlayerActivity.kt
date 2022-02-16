package com.skuad.retrofit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import com.skuad.retrofit.R
import com.skuad.retrofit.databinding.ActivityAddEmployeeBinding
import com.skuad.retrofit.databinding.ActivityAddPlayerBinding
import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.model.Player
import com.skuad.retrofit.remote.RetrofitClient
import com.skuad.retrofit.viewmodel.AddEmployeeViewModel
import com.skuad.retrofit.viewmodel.AddEmployeeViewModelFactory
import com.skuad.retrofit.viewmodel.AddPlayerViewModel
import com.skuad.retrofit.viewmodel.AddPlayerViewModelFactory

class AddPlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddPlayerBinding
    private val qualificationList = listOf("Select Qualification", "M.Tech", "B.E", "MSC","BSC","MBA","B.COM","M.COM")
    private val sportDepartmentList = listOf("Select Department", "HOCKY", "CRICKET","TABLE_TANNISH","HOLLY_BALL","CHESS","RUNNING")

    private val viewModel: AddPlayerViewModel by viewModels(){
        AddPlayerViewModelFactory(RetrofitClient.apiInterface)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val qualificationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, qualificationList)
        binding.spnQualification.adapter = qualificationAdapter

        val sportDepartmentAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sportDepartmentList)
        binding.spnSportDepartment.adapter = sportDepartmentAdapter

        binding.btnSave.setOnClickListener {
            getData()
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.playerLiveData.observe(this, { player ->
            binding.progress.visibility = View.GONE
            resetData()
            showToast("Player added successfully")
        })
    }

    private fun resetData() {
        with(binding){
            etFirstName.setText("")
            etLastName.setText("")
            etDateOfBirth.setText("")
            etDesignation.setText("")
            etAddress.setText("")
            rgGender.clearCheck()
            spnQualification.setSelection(0)
            spnSportDepartment.setSelection(0)
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
        // DateOfBirth

        val dateOfBirth = binding.etDateOfBirth.text.toString().trim()

        if (dateOfBirth.isEmpty()) {
            showToast("Please enter dateOfBirth")
            return
        }
        // Gender

        var gender = ""

        val selectedId = binding.rgGender.checkedRadioButtonId

        val radioButton = findViewById<RadioButton>(selectedId)

        if (radioButton == null) {
            showToast("Please select gender")
            return
        } else{
            gender = radioButton.text.toString()
        }

        // Qualification
        val qualification = binding.spnQualification.selectedItem as String
        if (qualification == "Select Qualification") {
            showToast("Please select Qualification")
            return
        }

        // Sport Department
        val sportDepartment = binding.spnSportDepartment.selectedItem as String
        if (sportDepartment =="Select SportDepartment") {
            showToast("Please enter SportDepartment")
            return
        }
        // Designation

        val designation = binding.etDesignation.text.toString().trim()

        if (designation.isEmpty()) {
            showToast("Please enter designation")
            return
        }
        // Address

        val address = binding.etAddress.text.toString().trim()

        if (address.isEmpty()) {
            showToast("Please enter address")
            return
        }


        val player = Player(
            firstName = firstName,
            lastName = lastName,
            qualification = qualification,
            sportDepartment = sportDepartment,
            dateOfBirth = dateOfBirth,
            gender = gender,
            address = address,
            designation = designation


        )
        viewModel.addPlayer(player)
        binding.progress.visibility = View.VISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}







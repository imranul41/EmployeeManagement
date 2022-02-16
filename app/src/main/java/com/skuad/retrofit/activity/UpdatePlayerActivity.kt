package com.skuad.retrofit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import com.skuad.employeemanagement.extension.hideKeyboard
import com.skuad.retrofit.R
import com.skuad.retrofit.databinding.ActivityAddEmployeeBinding
import com.skuad.retrofit.databinding.ActivityUpdateEmployeeBinding
import com.skuad.retrofit.databinding.ActivityUpdatePlayerBinding
import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.model.Player
import com.skuad.retrofit.remote.RetrofitClient
import com.skuad.retrofit.viewmodel.UpdateEmployeeViewModel
import com.skuad.retrofit.viewmodel.UpdateEmployeeViewModelFactory
import com.skuad.retrofit.viewmodel.UpdatePlayerViewModel
import com.skuad.retrofit.viewmodel.UpdatePlayerViewModelFactory

class UpdatePlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdatePlayerBinding

    private val qualificationList = listOf("Select Qualification", "M.Tech", "B.E", "MSC","BSC","MBA","B.COM","M.COM")
    private val sportDepartmentList = listOf("Select Department", "HOCKY", "CRICKET","TABLE_TANNISH","HOLLY_BALL","CHESS","RUNNING")

    private val viewModel: UpdatePlayerViewModel by viewModels() {
        UpdatePlayerViewModelFactory(RetrofitClient.apiInterface)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(binding.root)
        setupSpinner()
        addClickEvent()
        observeLiveData()

    }

    private fun setupSpinner() {
        val qualificationAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, qualificationList)
        binding.spnQualification.adapter = qualificationAdapter

        val sportDepartmentAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sportDepartmentList)
        binding.spnSportDepartment.adapter = sportDepartmentAdapter
    }

    private fun observeLiveData() {
        viewModel.playerLiveData.observe(this, { player ->
            player?.let {
                showPlayer(it)
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


    private fun showPlayer(player: Player) {
        with(binding) {

            // First Name
            etFirstName.setText(player.firstName)

            // Last Name
            etLastName.setText(player.lastName)

            // Date Of Birth
            etDateOfBirth.setText(player.dateOfBirth)

            // Designation
            etDesignation.setText(player.designation)

            // Qualification
            val indexOfQualification = qualificationList.indexOf(player.qualification)
            spnQualification.setSelection(indexOfQualification)

            // Sport Department
            val indexOfSportDepartment= sportDepartmentList.indexOf(player.sportDepartment)
            spnSportDepartment.setSelection(indexOfSportDepartment)

            // Address
            etAddress.setText(player.address)

        }
    }

    private fun addClickEvent() {
        binding.btnSearch.setOnClickListener {
            binding.btnSearch.hideKeyboard()
            val playerId = binding.etPlayerId.text.toString().trim()
            if (playerId.isEmpty()) {
                Toast.makeText(this, "Player id is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.getPlayer(playerId)
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
        // Date Of Birth

        val dateOfBirth = binding.etDateOfBirth.text.toString().trim()

        if (dateOfBirth.isEmpty()) {
            showToast("Please enter dateOfBirth")
            return
        }
        // Designation

        val designation = binding.etDesignation.text.toString().trim()

        if (designation.isEmpty()) {
            showToast("Please enter designation")
            return
        }

        // Qualification
        val qualification = binding.spnQualification.selectedItem as String
        if (qualification == "Select Qualification") {
            showToast("Please select qualification")
            return
        }

        // Sport Department
        val sportDepartment = binding.spnSportDepartment.selectedItem as String
        if (sportDepartment == "Select SportDepartment") {
            showToast("Please enter sportDepartment")
            return
        }

        // Address

        val address = binding.etAddress.text.toString().trim()

        if (address.isEmpty()) {
            showToast("Please enter address")
            return
        }
        // Gender

        var gender = ""

        val selectedId = binding.rgGender.checkedRadioButtonId

        val radioButton = findViewById<RadioButton>(selectedId)

        if (radioButton == null) {
            showToast("Please select gender")
            return
        } else {
            gender = radioButton.text.toString()
        }


        val player = Player(
            firstName = firstName,
            lastName = lastName,
            designation = designation,
            sportDepartment = sportDepartment,
            address = address,
            qualification = qualification,
            dateOfBirth = dateOfBirth,
            gender = gender
        )


        val playerId = binding.etPlayerId.text.toString().trim()
        if (playerId.isEmpty()) {
            Toast.makeText(this, "Player id is required", Toast.LENGTH_SHORT).show()
            return
        }

        player.id = playerId.toInt()
        viewModel.updatePlayer(player)
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}



package com.skuad.retrofit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.skuad.retrofit.adapter.ShowEmployeeAdapter
import com.skuad.retrofit.databinding.ActivityShowEmployeeBinding
import com.skuad.retrofit.remote.RetrofitClient
import com.skuad.retrofit.viewmodel.ShowEmployeeViewModel
import com.skuad.retrofit.viewmodel.ShowEmployeeViewModelFactory

class ShowEmployeeActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowEmployeeBinding

    private val viewModel: ShowEmployeeViewModel by viewModels() {
        ShowEmployeeViewModelFactory(RetrofitClient.apiInterface)
    }
    lateinit var adapter: ShowEmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setAdapter()
        observeData()
    }


    private fun observeData() {
        viewModel.employeeLiveData.observe(this, {
            Log.d("Data", "Response data is $it")
            adapter.submitList(it)
        })
    }

    private fun loadData() {
        viewModel.getAllEmployee()
    }
    private fun setAdapter() {
        adapter = ShowEmployeeAdapter(onItemClick = { employee ->
            Toast.makeText(this, "Onclick ${employee.firstName}", Toast.LENGTH_SHORT).show()
        }, onDeleteClick = { employee ->
            viewModel.deleteEmployee(employee)
        })

        binding.rvShowEmployee.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.rvShowEmployee.adapter = adapter

    }

}

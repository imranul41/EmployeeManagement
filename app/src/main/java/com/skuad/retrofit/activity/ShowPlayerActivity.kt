package com.skuad.retrofit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.skuad.retrofit.R
import com.skuad.retrofit.adapter.ShowEmployeeAdapter
import com.skuad.retrofit.adapter.ShowPlayerAdapter
import com.skuad.retrofit.databinding.ActivityShowEmployeeBinding
import com.skuad.retrofit.databinding.ActivityShowPlayerBinding
import com.skuad.retrofit.remote.RetrofitClient
import com.skuad.retrofit.viewmodel.ShowEmployeeViewModel
import com.skuad.retrofit.viewmodel.ShowEmployeeViewModelFactory
import com.skuad.retrofit.viewmodel.ShowPlayerViewModel
import com.skuad.retrofit.viewmodel.ShowPlayerViewModelFactory

class ShowPlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowPlayerBinding

    private val viewModel: ShowPlayerViewModel by viewModels() {
        ShowPlayerViewModelFactory(RetrofitClient.apiInterface)
    }
    lateinit var adapter: ShowPlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setAdapter()
        observeData()
    }


    private fun observeData() {
        viewModel.playerLiveData.observe(this, {
            Log.d("Data", "Response data is $it")
            adapter.submitList(it)
        })
    }

    private fun loadData() {
        viewModel.getAllPlayer()
    }
    private fun setAdapter() {
        adapter = ShowPlayerAdapter(onItemClick = { player ->
            Toast.makeText(this, "Onclick ${player.firstName}", Toast.LENGTH_SHORT).show()
        }, onDeleteClick = { player ->
            viewModel.deletePlayer(player)
        })

        binding.rvShowPlayer.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.rvShowPlayer.adapter = adapter

    }

}

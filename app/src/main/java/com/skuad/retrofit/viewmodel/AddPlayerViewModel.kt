package com.skuad.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skuad.retrofit.model.Player
import com.skuad.retrofit.remote.ApiInterface
import kotlinx.coroutines.launch
import retrofit2.Response

class AddPlayerViewModel(private val apiInterface: ApiInterface) : ViewModel() {

    val playerLiveData = MutableLiveData<Response<Player>>()

    fun addPlayer(player: Player) {
        viewModelScope.launch {
            playerLiveData.value = apiInterface.addPlayer(player)
        }
    }

}
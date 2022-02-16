package com.skuad.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.model.Player
import com.skuad.retrofit.remote.ApiInterface
import kotlinx.coroutines.launch

class UpdatePlayerViewModel(private val apiInterface: ApiInterface) : ViewModel() {

    val playerLiveData = MutableLiveData<Player?>()

    val updateLiveData = MutableLiveData<Boolean>()

    fun getPlayer(id: String) {
        viewModelScope.launch {
            val response = apiInterface.getPlayer(id.toInt())
            if(response.isSuccessful){
                playerLiveData.value = response.body()
            }
        }
    }

    fun updatePlayer(player: Player) {
        viewModelScope.launch {
            val response = apiInterface.updatePlayer(id = player.id, player = player)
            updateLiveData.value = response.isSuccessful
        }
    }

}
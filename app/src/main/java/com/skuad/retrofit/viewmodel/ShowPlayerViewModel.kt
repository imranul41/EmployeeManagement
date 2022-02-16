package com.skuad.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.model.Player
import com.skuad.retrofit.remote.ApiInterface
import kotlinx.coroutines.launch

class ShowPlayerViewModel(private val apiInterface: ApiInterface) : ViewModel() {
    val playerLiveData = MutableLiveData<List<Player>>()


    fun getAllPlayer() {
        viewModelScope.launch {
            playerLiveData.value = apiInterface.getAllPlayer()
        }
    }
    fun deletePlayer(player: Player) {
        viewModelScope.launch {
            val response = apiInterface.deletePlayer(player.id)
            if(response.isSuccessful){
                playerLiveData.value = apiInterface.getAllPlayer()
            }
        }
    }

}

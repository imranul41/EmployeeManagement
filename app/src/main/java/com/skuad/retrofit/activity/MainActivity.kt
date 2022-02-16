package com.skuad.retrofit.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skuad.retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShow.setOnClickListener {
            intent = Intent(this, ShowEmployeeActivity::class.java)
            startActivity(intent)
        }
        binding.btnAdd.setOnClickListener {
            intent = Intent (this,  AddEmployeeActivity::class.java)
            startActivity(intent)
        }
        binding.btnUpdate.setOnClickListener {
            intent = Intent(this, UpdateEmployeeActivity::class.java)
            startActivity(intent)
        }
        binding.btnAddPlayer.setOnClickListener {
            intent = Intent(this,  AddPlayerActivity::class.java)
            startActivity(intent)
        }
        binding.btnShowPlayer.setOnClickListener {
            intent = Intent(this, ShowPlayerActivity::class.java)
            startActivity(intent)
        }
        binding.btnUpdatePlayer.setOnClickListener {
            intent = Intent(this, UpdatePlayerActivity::class.java)
            startActivity(intent)
        }


    }
}

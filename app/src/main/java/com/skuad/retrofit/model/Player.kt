package com.skuad.retrofit.model

data class Player(
    var id: Int = 0,
    val address: String,
    val dateOfBirth: String,
    val designation: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val qualification: String,
    val sportDepartment: String
)
package com.skuad.retrofit.model

data class Employee(
    val address: String,
    val department: String,
    val designation: String,
    val firstName: String,
    val lastName: String,
    var id: Int = 0
)
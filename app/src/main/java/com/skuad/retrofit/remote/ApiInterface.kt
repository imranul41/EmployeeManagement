package com.skuad.retrofit.remote

import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.model.Player
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("/employees")
    suspend fun getAllEmployee(): List<Employee>

    @POST("/employees")
    suspend fun addEmployee(@Body employee: Employee): Response<Employee>

    @GET("/employees/{id}")
    suspend fun getEmployee(@Path("id") id: Int): Response<Employee>

    @DELETE("/employees/{id}")
    suspend fun deleteEmployee(@Path("id") id: Int): Response<Unit>

    @PUT("/employees/{id}")
    suspend fun updateEmployee(@Path("id") id: Int, @Body employee: Employee): Response<Employee>


    @POST("/players")
    suspend fun addPlayer(@Body player: Player): Response<Player>

    @GET("/players")
    suspend fun getAllPlayer(): List<Player>

    @GET("/players/{id}")
    suspend fun getPlayer(@Path("id") id: Int): Response<Player>

    @DELETE("/players/{id}")
    suspend fun deletePlayer(@Path("id") id: Int): Response<Unit>

    @PUT("/players/{id}")
    suspend fun updatePlayer(@Path("id") id: Int, @Body player: Player): Response<Player>





}
package com.example.demoapktask


import retrofit2.Call
import retrofit2.http.GET

interface myInterface {

    @GET("test-api")
    fun getData(): Call<myModel>
}
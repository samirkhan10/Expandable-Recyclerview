package com.example.demoapktask

data class myModel(
    val code: Int,
    val error: Boolean,
    val message: String,
    val response: ArrayList<Response>
)
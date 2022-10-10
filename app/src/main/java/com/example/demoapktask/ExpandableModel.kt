package com.example.demoapktask

data class ExpandableModel(
    val title: String,
    val data: ArrayList<Response>,
    val availableSlots: Int,
    var isExpanded: Boolean = false
    )
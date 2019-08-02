package com.example.loicuabac2.entity

import com.google.gson.annotations.SerializedName

data class MainMenu(
    @SerializedName("id")
    var id: String,
    @SerializedName("tenMenu")
    var tenMenu: String
)
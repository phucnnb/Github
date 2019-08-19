package com.example.loicuabac2.entity

import com.google.gson.annotations.SerializedName

data class CategoryImage (

    @SerializedName("tenhinh")
    var nameImage: String,

    @SerializedName("linkhinh")
    var linkImage: String
)
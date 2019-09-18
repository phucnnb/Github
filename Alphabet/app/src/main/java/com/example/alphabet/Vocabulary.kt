package com.example.alphabet

import com.google.gson.annotations.SerializedName

data class Vocabulary (
    @SerializedName("id")
    var id : Int,
    @SerializedName("vietnamese")
    var vietnamese : String,
    @SerializedName("pronounce")
    var pronounce : String,
    @SerializedName("typeface")
    var typeface : String
)

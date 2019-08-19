package com.example.loicuabac2.entity

import com.google.gson.annotations.SerializedName

data class CategoryStory (
    @SerializedName("tentruyen")
    var tentruyen: String,

    @SerializedName("linktruyen")
    var linktruyen: String,

    @SerializedName("id")
    var id: String
)
package com.example.loicuabac2.entity

import com.google.gson.annotations.SerializedName

data class CategoryStory (
    @SerializedName("tentruyen")
    var nameStory: String,

    @SerializedName("linktruyen")
    var linkStory: String,

    @SerializedName("id")
    var idStory: String
)
package com.example.loicuabac2.service.download

import org.json.JSONObject

class ParseData() {

    fun getIdSpecies (data : String): Int {
        val jsonSpecies = JSONObject(data)
        val id = jsonSpecies.getInt("chucvu")
        return id
    }
}
package com.example.loicuabac2.service.retrofit


import com.example.alphabet.Vocabulary
import retrofit2.Call
import retrofit2.http.GET

interface DataClient {
    @GET("loadVocabulary.php")
    fun getVocabulary(): Call<List<Vocabulary>>
}
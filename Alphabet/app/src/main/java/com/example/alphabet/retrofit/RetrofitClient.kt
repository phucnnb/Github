package com.example.alphabet.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    object RetrofitgetClient{

        private var retrofit: Retrofit? = null
        fun getClient(url: String): Retrofit {
            val builder = OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(100000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build()
            val gson = GsonBuilder().setLenient().create()
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit!!
        }
    }
}
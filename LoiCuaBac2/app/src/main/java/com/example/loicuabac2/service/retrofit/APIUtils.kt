package com.example.loicuabac2.service.retrofit

class APIUtils {
    val url = "http://ebook.xsolution.vn/loicuabac/"

    fun getData(): DataClient {
        return RetrofitClient.RetrofitgetClient.getClient(url).create(DataClient::class.java)
    }
}
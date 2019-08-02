package com.example.loicuabac2.service.retrofit

import com.example.loicuabac2.Constants

class APIUtils {


    object data{
        fun getData(): DataClient {
            return RetrofitClient.RetrofitgetClient.getClient(Constants.URL_SERVER).create(DataClient::class.java)
        }
    }

}
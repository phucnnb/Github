package com.example.alphabet.retrofit


class APIUtils {


    object data{
        fun getData(): DataClient {
            return RetrofitClient.RetrofitgetClient.getClient("http://ebook.xsolution.vn/phuc/").create(DataClient::class.java)
        }
    }

}
package com.example.rxandroidretrofit;

public class APIUtils {
    public static final String Base_Url = "https://api.learn2crack.com/";

    public  static DataClient getData(){
        return RetrofitCilent.getClient(Base_Url).create(DataClient.class);
    }
}

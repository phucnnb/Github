package com.example.mvpretrofit.Retrofit;

public class APIUtils {
    public static final String Base_Url = "https://baophuc.000webhostapp.com/demoRetrofit/";

    public  static DataClient getData(){
        return RetrofitCilent.getClient(Base_Url).create(DataClient.class);
    }
}

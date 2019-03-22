package com.example.mvpretrofit.Retrofit;

import com.example.mvpretrofit.Model.CheckKetQua;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataClient {
    @FormUrlEncoded
    @POST("insertUser.php")
    Call<CheckKetQua> InsertTaiKhoan(@Field("taikhoan") String taikhoan
            , @Field("matkhau") String matkhau);

    @FormUrlEncoded
    @POST("checkUser.php")
    Call<CheckKetQua> CheckUser(@Field("taikhoan") String taikhoan);

    @FormUrlEncoded
    @POST("Login.php")
    Call<CheckKetQua> LoginTaiKhoan(@Field("taikhoan") String taikhoan
            , @Field("matkhau") String matkhau);

}

package com.example.loicuabac2.service.retrofit

import com.example.loicuabac2.entity.ChildMenu
import com.example.loicuabac2.entity.MainMenu
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface DataClient {
    @GET("php/menu.php")
    fun titleMenu(): Call<List<MainMenu>>

    @FormUrlEncoded
    @POST("php/childmenu.php")
    fun childMenu(@Field("idMenu") idMenu: String): Call<List<ChildMenu>>

   /* @FormUrlEncoded
    @POST("php/loadhinhanhloadmore.php")
    abstract fun hinhanh(@Field("phanloai") phanloai: String, @Field("code") code: Int): Call<List<HinhAnh>>

    @FormUrlEncoded
    @POST("php/loadtruyenloadmore.php")
    abstract fun truyen(@Field("phanloai") phanloai: String, @Field("code") code: Int): Call<List<Truyen>>*/
}
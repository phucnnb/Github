package com.example.mvpretrofit.Presenter.DangNhap;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mvpretrofit.Model.CheckKetQua;
import com.example.mvpretrofit.Model.User;
import com.example.mvpretrofit.Retrofit.APIUtils;
import com.example.mvpretrofit.Retrofit.DataClient;
import com.example.mvpretrofit.View.DangNhap.MainActivity;
import com.example.mvpretrofit.View.DangNhap.ViewXuLyDangNhap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicDangNhap implements PresenterImpXuLyDangNhap {
    ViewXuLyDangNhap viewXuLyDangNhap = null;
    SharedPreferences share;
    public PresenterLogicDangNhap(ViewXuLyDangNhap viewXuLyDangNhap) {
        this.viewXuLyDangNhap = viewXuLyDangNhap;
    }

    @Override
    public void ThucHienDangNhap(final User user, final Boolean check) {

        final String thongbao = "Chào Bạn " + user.getTaikhoan() + " Ngày Mới Vui Vẻ" ;
        DataClient dataClient = APIUtils.getData();
        Call<CheckKetQua> ketQuaCall = dataClient.LoginTaiKhoan(user.getTaikhoan(),user.getMatkhau());
        ketQuaCall.enqueue(new Callback<CheckKetQua>() {
            @Override
            public void onResponse(Call<CheckKetQua> call, Response<CheckKetQua> response) {
                CheckKetQua checkKetQua = new CheckKetQua();
                if (viewXuLyDangNhap != null){
                    if(response != null){
                        checkKetQua = response.body();
                        if(checkKetQua.getKetqua().equals("1")){
                            viewXuLyDangNhap.DangNhapThanhCong(thongbao);
                            if (check){
                                SharedPreferences.Editor editor = share.edit();
                                editor.putString("SDT",user.getTaikhoan());
                                editor.putString("MATKHAU",user.getMatkhau());
                                editor.putBoolean("CHECK",check);
                                editor.commit();
                            }else {
                                SharedPreferences.Editor editor = share.edit();
                                editor.putBoolean("CHECK",check);
                                editor.commit();
                            }
                        }else {
                            viewXuLyDangNhap.DangNhapThatBai();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckKetQua> call, Throwable t) {

            }
        });

    }

    @Override
    public void ThucHienDangKi() {
        viewXuLyDangNhap.DangKi();
    }

    @Override
    public void ThucHienXoaView() {
        viewXuLyDangNhap = null;
    }

    @Override
    public void ThucHienCheck(MainActivity mainActivity, Boolean check) {
        share = mainActivity.getSharedPreferences("sharePre", Context.MODE_PRIVATE);
        boolean kiemtra = share.getBoolean("CHECK",false);

        if(kiemtra){
            String sdt = share.getString("SDT","");
            String matkhau = share.getString("MATKHAU", "");
            viewXuLyDangNhap.Check(kiemtra,sdt,matkhau);

        }else {
            viewXuLyDangNhap.Check(kiemtra,null,null);
        }

    }
}

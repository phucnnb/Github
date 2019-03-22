package com.example.mvpretrofit.Presenter.DangNhap;

import com.example.mvpretrofit.Model.CheckKetQua;
import com.example.mvpretrofit.Model.User;
import com.example.mvpretrofit.Retrofit.APIUtils;
import com.example.mvpretrofit.Retrofit.DataClient;
import com.example.mvpretrofit.View.DangNhap.ViewXuLyDangNhap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicDangNhap implements PresenterImpXuLyDangNhap {
    ViewXuLyDangNhap viewXuLyDangNhap;

    public PresenterLogicDangNhap(ViewXuLyDangNhap viewXuLyDangNhap) {
        this.viewXuLyDangNhap = viewXuLyDangNhap;
    }

    @Override
    public void ThucHienDangNhap(User user) {

        final String thongbao = "Chào Bạn " + user.getTendangnhap() + " Ngày Mới Vui Vẻ" ;
        DataClient dataClient = APIUtils.getData();
        Call<CheckKetQua> ketQuaCall = dataClient.LoginTaiKhoan(user.getTendangnhap(),user.getMatkhau());
        ketQuaCall.enqueue(new Callback<CheckKetQua>() {
            @Override
            public void onResponse(Call<CheckKetQua> call, Response<CheckKetQua> response) {
                CheckKetQua checkKetQua = new CheckKetQua();
                if(response != null){
                    checkKetQua = response.body();
                    if(checkKetQua.getKetqua().equals("1")){
                        viewXuLyDangNhap.DangNhapThanhCong(thongbao);
                    }else {
                        viewXuLyDangNhap.DangNhapThatBai();
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
}

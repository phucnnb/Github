package com.example.mvpretrofit.Presenter.DangKi;

import android.content.Intent;

import com.example.mvpretrofit.Model.CheckKetQua;
import com.example.mvpretrofit.R;
import com.example.mvpretrofit.Retrofit.APIUtils;
import com.example.mvpretrofit.Retrofit.DataClient;
import com.example.mvpretrofit.View.DangKi.DangKi;
import com.example.mvpretrofit.View.DangKi.ViewXuLyDangKi;
import com.example.mvpretrofit.View.DangNhap.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicXuLyDangKi implements PresenterImpXuLyDangKi{
    ViewXuLyDangKi viewXuLyDangKi;

    public PresenterLogicXuLyDangKi(ViewXuLyDangKi viewXuLyDangKi) {
        this.viewXuLyDangKi = viewXuLyDangKi;
    }



    @Override
    public void ThucHienDangKi(final DangKi dangKi, final String taikhoan, final String matkhau, final String matkhauAgain) {

        DataClient dataClient = APIUtils.getData();
        Call<CheckKetQua> callback = dataClient.CheckUser(taikhoan);
        callback.enqueue(new Callback<CheckKetQua>() {
            @Override
            public void onResponse(Call<CheckKetQua> call, Response<CheckKetQua> response) {
                CheckKetQua checkKetQua = new CheckKetQua();
                if(response != null){
                    checkKetQua = response.body();
                    if(checkKetQua.getKetqua().equals("1")){
                        DangKi(dangKi,taikhoan,matkhau,matkhauAgain);
                    }else {
                        viewXuLyDangKi.DangKiTaiKhoan(R.string.tai_khoan_da_co);
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckKetQua> call, Throwable t) {

            }
        });
    }

    private void DangKi(final DangKi dangKi, final String taikhoan, String matkhau, String matkhauAgain) {
        final int[] thongbao = {0};
        if(matkhau.equals(matkhauAgain)){
            DataClient dataClient = APIUtils.getData();
            Call<CheckKetQua> checkKetQuaCall = dataClient.InsertTaiKhoan(taikhoan,matkhau);
            checkKetQuaCall.enqueue(new Callback<CheckKetQua>() {
                @Override
                public void onResponse(Call<CheckKetQua> call, Response<CheckKetQua> response) {
                    CheckKetQua checkKetQua = new CheckKetQua();
                    if(response != null){
                        checkKetQua = response.body();
                        if(checkKetQua.getKetqua().equals("1")){
                            thongbao[0] = R.string.dang_ki_thanh_cong;
                            viewXuLyDangKi.DangKiTaiKhoan(thongbao[0]);
                            Intent i = new Intent(dangKi, MainActivity.class);
                            dangKi.startActivity(i);
                            dangKi.finish();
                        }else{
                            thongbao[0] = R.string.dang_ki_khong_thanh_cong;
                        }
                    }

                }

                @Override
                public void onFailure(Call<CheckKetQua> call, Throwable t) {

                }
            });
        }else {
            int thongbao1 = R.string.mat_khau_xac_nhan_sai;
            viewXuLyDangKi.DangKiTaiKhoan(thongbao1);
        }
    }

    @Override
    public void Clear() {
        viewXuLyDangKi.Clear();
    }
}

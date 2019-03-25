package com.example.mvpretrofit.Presenter.DangKi;

import com.example.mvpretrofit.View.DangKi.DangKi;

public interface PresenterImpXuLyDangKi {
    void ThucHienDangKi(DangKi dangKi, String taikhoan, String matkhau, String matkhauAgain);
    void Clear();
    void ThucHienXoaView();
}

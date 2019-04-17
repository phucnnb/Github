package com.example.demofirebase.Presenter.DangNhap;

import android.content.Context;


public interface PresenterImpDangNhap {
    void ThucHienDangNhap(String email, String mKhau, Boolean check);
    void ThucHienXoaView();
    void ThucHienChuyenDangKy(Context dangNhap);
    void ThucHienCheck(boolean checked);
}

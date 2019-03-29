package com.example.mvpretrofit.Presenter.DangNhap;

import com.example.mvpretrofit.Model.User;
import com.example.mvpretrofit.View.DangNhap.MainActivity;

public interface PresenterImpXuLyDangNhap  {
    void ThucHienDangNhap(User user, Boolean check);
    void ThucHienDangKi();
    void ThucHienXoaView();
    void ThucHienCheck(MainActivity mainActivity, Boolean check);
}

package com.example.demofirebase.Presenter.DangKi;

import android.content.Intent;

public interface PresenterImpXuLyDangKy {
    void ThucHienDangKi(String email, String matKhau, String matKhauAgain);
    void ThucHienSetImage(int requestCode, int resultCode, Intent data);
    void ThucHienClear();
    void ThucHienXoaView();
}

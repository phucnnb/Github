package com.example.mvpretrofit.View.TrangChu;

import com.example.mvpretrofit.Model.Android;
import com.example.mvpretrofit.Model.User;

import java.util.List;

public interface ViewXuLyTrangChu {
    void LayDuLieuUser(List<User> listUser);
    void LayDuLieuAndroid(List<Android> listAndroid);
    void XuLyRecycler();
}

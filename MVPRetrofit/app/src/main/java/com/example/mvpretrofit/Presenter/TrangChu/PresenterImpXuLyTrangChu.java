package com.example.mvpretrofit.Presenter.TrangChu;

import android.support.v7.widget.RecyclerView;

public interface PresenterImpXuLyTrangChu {
    void ThucHienLayDuLieuUser(RecyclerView recycler);
    void ThucHienLayDuLieuAndroid(RecyclerView recycler);
    void ThucHienRecyclerView();
    void ThucHienXoaView();
    void ThucHienClearData();
}

package com.example.demofirebase.View.TrangChu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.demofirebase.Adapter.AdapterUser;
import com.example.demofirebase.Model.User;
import com.example.demofirebase.Presenter.DangNhap.PresenterLogicDangNhap;
import com.example.demofirebase.Presenter.TrangChu.PresenterLogicTrangChu;
import com.example.demofirebase.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrangChu extends AppCompatActivity implements ViewXuLyTrangChu{

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private PresenterLogicTrangChu logicTrangChu;
    private List<User> listUser;
    private AdapterUser adapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        ButterKnife.bind(this);
        logicTrangChu = new PresenterLogicTrangChu(this,TrangChu.this);

        listUser = new ArrayList();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        adapterUser = new AdapterUser(this);
        logicTrangChu.ThucHienClearData();
        logicTrangChu.ThucHienGetData();




    }

    @Override
    public void GetData(List<User> listUser) {
        adapterUser.setUsers(listUser);
        recycler.setAdapter(adapterUser);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logicTrangChu.ThucHienXoaView();
    }
}

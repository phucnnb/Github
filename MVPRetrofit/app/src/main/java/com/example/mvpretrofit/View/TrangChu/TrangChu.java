package com.example.mvpretrofit.View.TrangChu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.mvpretrofit.Adapter.AdapterAndroid;
import com.example.mvpretrofit.Adapter.AdapterUser;
import com.example.mvpretrofit.Model.Android;
import com.example.mvpretrofit.Model.User;
import com.example.mvpretrofit.Presenter.TrangChu.PresenterLogicTrangChu;
import com.example.mvpretrofit.R;

import java.util.List;


public class TrangChu extends AppCompatActivity implements ViewXuLyTrangChu {

    TextView txtTitle;
    LinearLayout layoutUser, layoutAndroid;
    PresenterLogicTrangChu logicTrangChu;
    RecyclerView recycler;
    AdapterAndroid adapterAndroid;
    AdapterUser adapterUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        logicTrangChu = new PresenterLogicTrangChu(this,this);
        init();
        event();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        adapterAndroid = new AdapterAndroid(this);
        adapterUser = new AdapterUser(this);



    }


    // xử lí sự kiên user và android
    private void event() {
        layoutAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logicTrangChu.ThucHienClearData();
                logicTrangChu.ThucHienLayDuLieuAndroid();
            }
        });

        layoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logicTrangChu.ThucHienClearData();
                logicTrangChu.ThucHienLayDuLieuUser();
            }
        });
    }

    // khai báo
    private void init() {
        txtTitle = findViewById(R.id.txtTitle);
        layoutAndroid = findViewById(R.id.layoutAndroid);
        layoutUser = findViewById(R.id.layoutPerson);
        recycler = findViewById(R.id.recycler);
    }

    @Override
    public void LayDuLieuUser(List<User> listUser) {
        txtTitle.setText("User");
        adapterUser.setUsers(listUser);
        recycler.setAdapter(adapterUser);

    }

    @Override
    public void LayDuLieuAndroid(List<Android> listAndroid) {
        txtTitle.setText("Android");
        adapterAndroid.setAndroids(listAndroid);
        recycler.setAdapter(adapterAndroid);
    }

    @Override
    public void XuLyRecycler() {

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        logicTrangChu.ThucHienXoaView();
    }
}

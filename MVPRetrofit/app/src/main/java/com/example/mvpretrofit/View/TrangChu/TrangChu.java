package com.example.mvpretrofit.View.TrangChu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.example.mvpretrofit.Presenter.TrangChu.PresenterLogicTrangChu;
import com.example.mvpretrofit.R;


public class TrangChu extends AppCompatActivity implements ViewXuLyTrangChu {

    TextView txtTitle;
    LinearLayout layoutUser, layoutAndroid;
    PresenterLogicTrangChu logicTrangChu;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        logicTrangChu = new PresenterLogicTrangChu(this,this);
        init();
        event();
    }


    // xử lí sự kiên user và android
    private void event() {
        layoutAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logicTrangChu.ThucHienClearData();
                logicTrangChu.ThucHienLayDuLieuAndroid(recycler);
            }
        });

        layoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logicTrangChu.ThucHienClearData();
                logicTrangChu.ThucHienLayDuLieuUser(recycler);
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
    public void LayDuLieuUser() {
        txtTitle.setText("User");
    }

    @Override
    public void LayDuLieuAndroid() {
        txtTitle.setText("Android");
    }

    @Override
    public void XuLyRecycler() {

    }

    @Override
    public void Clear() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logicTrangChu.ThucHienXoaView();
    }
}

package com.example.mvpretrofit.View.DangNhap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpretrofit.Model.User;
import com.example.mvpretrofit.Presenter.DangNhap.PresenterLogicDangNhap;
import com.example.mvpretrofit.R;
import com.example.mvpretrofit.View.DangKi.DangKi;

public class MainActivity extends AppCompatActivity implements ViewXuLyDangNhap {

    private EditText editTenDangNhap, editMatKhau;
    private Button btnDangNhap,btnDangKi;
    private PresenterLogicDangNhap logicDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        logicDangNhap = new PresenterLogicDangNhap(this);
        event();
    }

    // xử lí sự kiện button
    private void event() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new
                        User(editTenDangNhap.getText().toString(),editMatKhau.getText().toString());
                logicDangNhap.ThucHienDangNhap(user);
            }
        });

        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logicDangNhap.ThucHienDangKi();
            }
        });
    }


    //khai bao
    private void init() {
        editTenDangNhap = findViewById(R.id.editDangNhap);
        editMatKhau = findViewById(R.id.editMKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKi = findViewById(R.id.btnDangKi);
    }

    @Override
    public void DangNhapThanhCong(String thongbao) {
        Toast.makeText(getApplicationContext(),thongbao,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void DangNhapThatBai() {
        Toast.makeText(getApplicationContext(),R.string.dang_nhap_that_bai,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void DangKi() {
        Intent intent = new Intent(MainActivity.this, DangKi.class);
        startActivity(intent);
    }
}

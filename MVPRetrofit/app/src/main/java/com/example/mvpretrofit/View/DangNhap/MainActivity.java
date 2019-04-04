package com.example.mvpretrofit.View.DangNhap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpretrofit.Model.User;
import com.example.mvpretrofit.Presenter.DangNhap.PresenterLogicDangNhap;
import com.example.mvpretrofit.R;
import com.example.mvpretrofit.View.DangKi.DangKi;
import com.example.mvpretrofit.View.TrangChu.TrangChu;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements ViewXuLyDangNhap {

    private EditText editTenDangNhap, editMatKhau;
    private TextView txtDangKi;
    private Button btnDangNhap;
    private PresenterLogicDangNhap logicDangNhap;
    private CheckBox cbSave;
    private SharedPreferences share;
    private ImageView image;
    private boolean press = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
        share = getSharedPreferences("sharePre",MODE_PRIVATE);
        logicDangNhap = new PresenterLogicDangNhap(this);
        kiemtraCheckBox();
        event();
    }

    private void kiemtraCheckBox() {
        logicDangNhap.ThucHienCheck(MainActivity.this,cbSave.isChecked());

    }

    // xử lí sự kiện button
    private void event() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new
                        User(editTenDangNhap.getText().toString(),editMatKhau.getText().toString());
                logicDangNhap.ThucHienDangNhap(user,cbSave.isChecked());
            }
        });

        txtDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logicDangNhap.ThucHienDangKi();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.custom(getApplicationContext(),"AVENGERS ASSEMBLE",R.drawable.avengers,R.color.colorBlack,Toasty.LENGTH_SHORT,true,true).show();
            }
        });
    }


    //khai bao
    private void init() {
        editTenDangNhap = findViewById(R.id.editDangNhap);
        editMatKhau = findViewById(R.id.editMKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        txtDangKi = findViewById(R.id.txtDangKi);
        cbSave = findViewById(R.id.cbSave);
        image = findViewById(R.id.imageMain);
    }

    @Override
    public void DangNhapThanhCong(String thongbao) {
        if(press){
            Toast.makeText(getApplicationContext(),thongbao,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, TrangChu.class);
            startActivity(intent);
            press = false;
            finish();
        }
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

    @Override
    public void Check(Boolean check, String sdt, String matkhau) {
        editTenDangNhap.setText(sdt);
        editMatKhau.setText(matkhau);
        cbSave.setChecked(check);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logicDangNhap.ThucHienXoaView();
        finish();
        
    }
}

package com.example.mvpretrofit.View.DangKi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpretrofit.Presenter.DangKi.PresenterLogicXuLyDangKi;
import com.example.mvpretrofit.R;

public class DangKi extends AppCompatActivity implements ViewXuLyDangKi {

    private EditText editDangKi,editMKhauDangKi,editMKhauDangKiAgain;
    private Button btnDangKiTaiKhoan,btnClear;
    private PresenterLogicXuLyDangKi xuLyDangKi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        init();
        xuLyDangKi = new PresenterLogicXuLyDangKi(this);
        event();
    }

    // xử lí sự kiện button
    private void event() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDangKi.Clear();
            }
        });


        btnDangKiTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"1111",Toast.LENGTH_SHORT).show();
                String taikhoan = editDangKi.getText().toString();
                String matkhau = editMKhauDangKi.getText().toString();
                String matkhauxacnhan = editMKhauDangKiAgain.getText().toString();

                xuLyDangKi.ThucHienDangKi(DangKi.this,taikhoan,matkhau,matkhauxacnhan);

            }
        });
    }



    //khai bao
    private void init() {
        editDangKi = findViewById(R.id.editDangKi);
        editMKhauDangKi = findViewById(R.id.editMKhauDangKi);
        editMKhauDangKiAgain = findViewById(R.id.editMKhauDangKiAgain);
        btnDangKiTaiKhoan = findViewById(R.id.btnDangKiTaiKhoan);
        btnClear = findViewById(R.id.btnClear);
    }



    @Override
    public void DangKiTaiKhoan(int thongbao) {
        Toast.makeText(this, thongbao, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void Clear() {
        editDangKi.setText("");
        editMKhauDangKi.setText("");
        editMKhauDangKiAgain.setText("");
        Toast.makeText(getApplicationContext(),"Làm Lại",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xuLyDangKi.ThucHienXoaView();
    }
}

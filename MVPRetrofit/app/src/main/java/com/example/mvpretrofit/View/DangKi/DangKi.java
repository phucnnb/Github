package com.example.mvpretrofit.View.DangKi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mvpretrofit.Presenter.DangKi.PresenterLogicXuLyDangKi;
import com.example.mvpretrofit.R;

import es.dmoral.toasty.Toasty;

public class DangKi extends AppCompatActivity implements ViewXuLyDangKi {

    private EditText editDangKi,editMKhauDangKi,editMKhauDangKiAgain;
    private Button btnDangKiTaiKhoan,btnClear;
    private ImageView imageDangKi;
    private PresenterLogicXuLyDangKi xuLyDangKi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        imageDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.custom(getApplicationContext(),"AVENGERS ASSEMBLE",R.drawable.avengers,R.color.colorBlack,Toasty.LENGTH_SHORT,true,true).show();
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
        imageDangKi = findViewById(R.id.imageDangKi);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xuLyDangKi.ThucHienXoaView();
    }
}

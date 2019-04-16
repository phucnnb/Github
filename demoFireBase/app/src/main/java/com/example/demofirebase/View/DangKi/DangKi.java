package com.example.demofirebase.View.DangKi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demofirebase.Presenter.DangKi.PresenterLogicXuLyDangKi;
import com.example.demofirebase.R;
import com.example.demofirebase.View.DangNhap.DangNhap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DangKi extends AppCompatActivity implements ViewXuLyDangKi {

    @BindView(R.id.btnDangKiTaiKhoan)
    Button btnDangKi;

    @BindView(R.id.btnClear)
    Button btnClear;

    @BindView(R.id.editDangKi)
    EditText editDangKi;

    @BindView(R.id.editMKhauDangKi)
    EditText editMKhauDangKi;

    @BindView(R.id.editMKhauDangKiAgain)
    EditText editMKhauDangKiAgain;

    @BindView(R.id.imageDangKi)
    ImageView imageDangKi;

    private PresenterLogicXuLyDangKi logicDangKy;
    private String email,matKhau,matKhauAgain;
    private int REQUEST_GET_SINGLE_FILE = 1;
    private int RESULT_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        ButterKnife.bind(this);
        logicDangKy = new PresenterLogicXuLyDangKi(this,DangKi.this);

        event(); // xử lí button
    }

    private void event() {

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logicDangKy.ThucHienClear();
            }
        });


        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editDangKi.getText().toString().trim();
                matKhau = editMKhauDangKi.getText().toString().trim();
                matKhauAgain = editMKhauDangKiAgain.getText().toString().trim();

                logicDangKy.ThucHienDangKi(email,matKhau,matKhauAgain);
            }
        });

        imageDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_GET_SINGLE_FILE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        logicDangKy.ThucHienSetImage(requestCode,resultCode,data);

    }

    @Override
    public void DangKiTaiKhoan(boolean check,int thongbao) {
        Toast.makeText(this,thongbao,Toast.LENGTH_SHORT).show();
        if(check){
            Intent intent = new Intent(this, DangNhap.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void SetHinhAnh(Bitmap bitmap) {
        imageDangKi.setImageBitmap(bitmap);
    }

    @Override
    public void Clear() {
        editDangKi.setText("");
        editMKhauDangKi.setText("");
        editMKhauDangKiAgain.setText("");
    }

    @Override
    public void XoaView() {

    }
}

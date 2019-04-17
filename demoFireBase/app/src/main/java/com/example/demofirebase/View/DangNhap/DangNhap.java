package com.example.demofirebase.View.DangNhap;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demofirebase.Presenter.DangNhap.PresenterLogicDangNhap;
import com.example.demofirebase.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DangNhap extends AppCompatActivity implements ViewXuLyDangNhap {

    @BindView(R.id.imageMain)
    ImageView imageView;

    @BindView(R.id.editDangNhap)
    EditText editDangNhap;

    @BindView(R.id.editMKhau)
    EditText editMKhau;

    @BindView(R.id.cbSave)
    CheckBox cbSave;

    @BindView(R.id.txtQuenMatKhau)
    TextView txtQuenMatKhau;

    @BindView(R.id.btnDangNhap)
    Button btnDangNhap;

    @BindView(R.id.txtDangKi)
    TextView txtDangKi;

    private PresenterLogicDangNhap logicDangNhap;
    private SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        ButterKnife.bind(this);
        logicDangNhap = new PresenterLogicDangNhap(this,DangNhap.this);
        share = getSharedPreferences("sharePre",MODE_PRIVATE);
        logicDangNhap.ThucHienCheck(cbSave.isChecked());
        event();
    }

    private void event() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editDangNhap.getText().toString();
                String mKhau = editMKhau.getText().toString();
                Boolean check = cbSave.isChecked();
                logicDangNhap.ThucHienDangNhap(email,mKhau,check);
            }
        });

        txtDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logicDangNhap.ThucHienChuyenDangKy(DangNhap.this);
            }
        });
    }

    @Override
    public void DangNhapTaiKhoan(int thongbao) {
        Toast.makeText(getApplicationContext(),thongbao,Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void ChuyenDangKy() {

    }

    @Override
    public void XoaView() {

    }

    @Override
    public void Check(boolean kiemtra, String email, String matkhau) {
        editDangNhap.setText(email);
        editMKhau.setText(matkhau);
        cbSave.setChecked(kiemtra);
    }
}

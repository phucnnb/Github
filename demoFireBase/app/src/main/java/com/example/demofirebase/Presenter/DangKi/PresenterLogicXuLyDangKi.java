package com.example.demofirebase.Presenter.DangKi;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.demofirebase.R;
import com.example.demofirebase.View.DangKi.DangKi;
import com.example.demofirebase.View.DangKi.ViewXuLyDangKi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;


public class PresenterLogicXuLyDangKi implements PresenterImpXuLyDangKy {

    private ViewXuLyDangKi viewXuLyDangKi;
    private Context context;
    private FirebaseDatabase mData = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    public PresenterLogicXuLyDangKi(ViewXuLyDangKi viewXuLyDangKi, Context context) {
        this.viewXuLyDangKi = viewXuLyDangKi;
        this.context = context;
    }


    @Override
    public void ThucHienDangKi(String email, String matKhau, String matKhauAgain) {
        if(email.length() != 0 && matKhau.length() != 0 && matKhauAgain.length() != 0){
            if(matKhau.equals(matKhauAgain)){
                mAuth.createUserWithEmailAndPassword(email, matKhau)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    viewXuLyDangKi.DangKiTaiKhoan(false,R.string.dang_ki_thanh_cong);
                                } else {
                                    viewXuLyDangKi.DangKiTaiKhoan(false,R.string.dang_ki_khong_thanh_cong);
                                }
                            }
                        });
            }else {
                viewXuLyDangKi.DangKiTaiKhoan(false, R.string.mat_khau_xac_nhan_sai);
            }
        }else {
            viewXuLyDangKi.DangKiTaiKhoan(false, R.string.hay_dien_day_du_thong_tin);
        }
    }

    @Override
    public void ThucHienUpImage() {
        viewXuLyDangKi.UploadHinhAnh();
    }

    @Override
    public void ThucHienClear() {
        viewXuLyDangKi.Clear();
    }

    @Override
    public void ThucHienXoaView() {
        viewXuLyDangKi.XoaView();
    }
}

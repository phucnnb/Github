package com.example.demofirebase.Presenter.DangNhap;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.demofirebase.R;
import com.example.demofirebase.View.DangKi.DangKi;
import com.example.demofirebase.View.DangNhap.ViewXuLyDangNhap;
import com.example.demofirebase.View.TrangChu.TrangChu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PresenterLogicDangNhap implements PresenterImpDangNhap {

    private ViewXuLyDangNhap viewXuLyDangNhap;
    private Context context;
    private SharedPreferences share;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    public PresenterLogicDangNhap(ViewXuLyDangNhap viewXuLyDangNhap, Context context) {
        this.viewXuLyDangNhap = viewXuLyDangNhap;
        this.context = context;
    }

    @Override
    public void ThucHienDangNhap(final String email, final String mKhau, final Boolean check) {
        mAuth.signInWithEmailAndPassword(email, mKhau)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(check){
                                SharedPreferences.Editor editor = share.edit();
                                editor.putString("EMAIL",email);
                                editor.putString("MATKHAU",mKhau);
                                editor.putBoolean("CHECK",check);
                                editor.commit();
                            }else {
                                SharedPreferences.Editor editor = share.edit();
                                editor.putString("EMAIL",email);
                                editor.putBoolean("CHECK",check);
                                editor.commit();
                            }
                            Intent intent = new Intent(context, TrangChu.class);
                            context.startActivity(intent);
                            viewXuLyDangNhap.DangNhapTaiKhoan(R.string.dang_nhap_thanh_cong);

                        } else {
                            // If sign in fails, display a message to the user.
                            viewXuLyDangNhap.DangNhapTaiKhoan(R.string.dang_nhap_that_bai);

                        }

                    }
                });
    }

    @Override
    public void ThucHienXoaView() {
        viewXuLyDangNhap = null;
    }

    @Override
    public void ThucHienChuyenDangKy(Context dangNhap) {
        Intent intent = new Intent(dangNhap, DangKi.class);
        dangNhap.startActivity(intent);
    }

    @Override
    public void ThucHienCheck(boolean checked) {
        share = context.getSharedPreferences("sharePre", Context.MODE_PRIVATE);
        boolean kiemtra = share.getBoolean("CHECK",false);

        if(kiemtra){
            String sdt = share.getString("EMAIL","");
            String matkhau = share.getString("MATKHAU", "");
            viewXuLyDangNhap.Check(kiemtra,sdt,matkhau);

        }else {
            viewXuLyDangNhap.Check(kiemtra,null,null);
        }

    }
}

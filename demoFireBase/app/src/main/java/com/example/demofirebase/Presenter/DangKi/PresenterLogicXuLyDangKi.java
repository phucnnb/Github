package com.example.demofirebase.Presenter.DangKi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.demofirebase.Model.User;
import com.example.demofirebase.R;
import com.example.demofirebase.View.DangKi.ViewXuLyDangKi;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class PresenterLogicXuLyDangKi implements PresenterImpXuLyDangKy {

    private ViewXuLyDangKi viewXuLyDangKi;
    private Context context;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReferenceFromUrl("gs://demofb-3de25.appspot.com");
    private int REQUEST_CODE_FOLDER = 1;
    private int RESULT_OK = -1;
    private String path;
    private Bitmap bitmap;
    private DatabaseReference mData = firebaseDatabase.getReference();
    private Boolean checkImage = false;


    public PresenterLogicXuLyDangKi(ViewXuLyDangKi viewXuLyDangKi, Context context) {
        this.viewXuLyDangKi = viewXuLyDangKi;
        this.context = context;
    }


    @Override
    public void ThucHienDangKi(final String email, String matKhau, String matKhauAgain) {
        if(checkImage){
            if(email.length() != 0 && matKhau.length() != 0 && matKhauAgain.length() != 0 ){
                if(matKhau.equals(matKhauAgain)){
                    mAuth.createUserWithEmailAndPassword(email, matKhau)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        UploadImage(email);
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
        }else {
            viewXuLyDangKi.DangKiTaiKhoan(false,R.string.chua_co_hinh_anh);
        }
    }

    private void UploadImage(final String email) {
        final StorageReference mountainsRef = storageRef.child(email+".png");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return mountainsRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    InsertProfile(email,downloadUri.toString());
                } else {

                }
            }
        });
    }


    private void InsertProfile(String email, String link) {
        User user = new User(email,link);
        mData.child("User").push().setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

            }
        });
    }

    @Override
    public void ThucHienSetImage(int requestCode, int resultCode, Intent data) {
        checkImage = true;
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            path = getRealPathFromURI(uri);
            try {
                InputStream inputStream = context.getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                viewXuLyDangKi.SetHinhAnh(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri){
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = ((Activity)context).managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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

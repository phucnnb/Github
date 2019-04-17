package com.example.demofirebase.Presenter.TrangChu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.demofirebase.Model.User;
import com.example.demofirebase.View.TrangChu.ViewXuLyTrangChu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicTrangChu implements PresenterImpTrangChu {

    private ViewXuLyTrangChu viewXuLyTrangChu;
    private Context context;
    private List<User> listUser = null;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mData = firebaseDatabase.getReference();

    public PresenterLogicTrangChu(ViewXuLyTrangChu viewXuLyTrangChu, Context context) {
        this.viewXuLyTrangChu = viewXuLyTrangChu;
        this.context = context;
    }

    @Override
    public void ThucHienGetData() {
        listUser = new ArrayList();
        mData.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class); // User phải có constructer select none
                Log.d("AAA", user.getEmail() + "  " + user.getLinkImage());
                listUser.add(user);
                viewXuLyTrangChu.GetData(listUser);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("AAA", dataSnapshot.toString() + 1);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void ThucHienXoaView() {
        viewXuLyTrangChu = null;
    }

    @Override
    public void ThucHienClearData() {
        if (listUser != null) {
            listUser.clear();
        }
    }
}

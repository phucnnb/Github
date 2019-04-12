package com.example.demofirebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demofirebase.Model.SinhVien;


public class MainActivity extends AppCompatActivity {

    TextView txtView;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* txtView = findViewById(R.id.txtText);
        btnAdd = findViewById(R.id.btnAdd);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference mData = firebaseDatabase.getReference();

        mData.child("HoTen").setValue("Bảo Phúc 1508");

        SinhVien sv = new SinhVien("Bảo Phúc",  13039201);

        mData.child("SinhVien").setValue(sv);*/

        /*mData.child("HocVien").push().setValue(sv, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError == null){
                    Toast.makeText(getApplicationContext(),"Xong",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Chưa Xong",Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        /*mData.child("SinhVien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("AAA",dataSnapshot.toString() + " aaaaaaaaaaaa");
                SinhVien sv1 = dataSnapshot.getValue(SinhVien.class);
                Log.d("AAA",sv1.toString() + " aaaaaaaaaaaa");
                String text = sv1.getHoTen() + " - " + sv1.getMsSinhVien();
                txtView.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        /*final int[] i = {0};
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.child("PhuongTien").push().setValue("Xe"+ i[0]);
                i[0] = i[0] +1;
            }
        });

        mData.child("PhuongTien").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                txtView.setText("");
                txtView.append(dataSnapshot.getValue().toString() + "\n");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
        });*/
    }
}

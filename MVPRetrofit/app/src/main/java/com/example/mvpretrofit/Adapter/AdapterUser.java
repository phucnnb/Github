package com.example.mvpretrofit.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mvpretrofit.Model.Android;
import com.example.mvpretrofit.Model.User;
import com.example.mvpretrofit.R;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserHolder> {

    Context context;
    List<User> users;

    public AdapterUser(Context context) {
        this.context = context;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    @NonNull
    @Override
    public AdapterUser.UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = layoutInflater.inflate(R.layout.item_user,viewGroup,false);
        UserHolder userHolder = new UserHolder(itemview);

        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.UserHolder userHolder, int i) {
        final User user = users.get(i);
        Log.d("CCC","User: " + user.getTaikhoan() + "\n" + "Password: " +user.getMatkhau());
        userHolder.tv_taikhoan.setText(user.getTaikhoan());
        userHolder.tv_matkhau.setText(user.getMatkhau());
        userHolder.cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"User: " + user.getTaikhoan() + "\n" + "Password: " +user.getMatkhau(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(users != null){
            return users.size();
        }else {
            return 0;
        }
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView tv_taikhoan,tv_matkhau;
        CardView cvUser;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tv_taikhoan = itemView.findViewById(R.id.tv_user);
            tv_matkhau = itemView.findViewById(R.id.tv_mkhau);
            cvUser = itemView.findViewById(R.id.cvUser);
        }
    }
}

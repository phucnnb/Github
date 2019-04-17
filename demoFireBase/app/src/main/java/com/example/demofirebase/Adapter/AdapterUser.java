package com.example.demofirebase.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demofirebase.Model.User;
import com.example.demofirebase.R;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserHolder> {

    private List<User> users;
    private Context context;
    private TextView txtProfile;
    private ImageView imageProfile;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mData = firebaseDatabase.getReference();

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
    public void onBindViewHolder(@NonNull UserHolder userHolder, int i) {
        final User user = users.get(i);
        Log.d("CCC","User: " + user.getEmail() + "\n" + "Link: " +user.getLinkImage());
        Picasso.with(context).load(user.getLinkImage()).resize(100,100).into(userHolder.imageUser);
        userHolder.txtUser.setText(user.getEmail());
        userHolder.cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view1 = inflater.inflate(R.layout.dialog_user,null);
                builder.setView(view1);

                initDialog(view1);

               txtProfile.setText(user.getEmail());

               mData.child("User").orderByChild("email").equalTo(user.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //User user = dataSnapshot.getValue(User.class);
                        User user= dataSnapshot.getChildren().iterator().next().getValue(User.class);
                        Picasso.with(context).load(user.getLinkImage()).resize(100,100).into(imageProfile);
                    }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
                });

                final AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void initDialog(View view1) {
        txtProfile = view1.findViewById(R.id.txtProfile);
        imageProfile = view1.findViewById(R.id.imageProfile);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        ImageView imageUser;
        TextView txtUser;
        CardView cvUser;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            imageUser = itemView.findViewById(R.id.imageUser);
            txtUser = itemView.findViewById(R.id.txtUser);
            cvUser = itemView.findViewById(R.id.cvUser);
        }
    }
}

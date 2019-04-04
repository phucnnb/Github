package com.example.mvpretrofit.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpretrofit.Model.Android;
import com.example.mvpretrofit.R;

import java.util.List;

public class AdapterAndroid extends RecyclerView.Adapter<AdapterAndroid.AndroidHolder> {
        Context context;
        List<Android> androids;

    public AdapterAndroid(Context context) {
        this.context = context;
    }

    public void setAndroids(List<Android> androids) {
        this.androids = androids;
    }

    @NonNull
    @Override
    public AdapterAndroid.AndroidHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = layoutInflater.inflate(R.layout.item_android,viewGroup,false);
        AndroidHolder androidHolder = new AndroidHolder(itemview);

        return androidHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAndroid.AndroidHolder androidHolder, int i) {

        final Android android = androids.get(i);
        androidHolder.tv_name.setText(android.getName());
        androidHolder.tv_version.setText(android.getVer());
        androidHolder.tv_api_level.setText(android.getApi());
        androidHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Name: " + android.getName() + "\n" + "Version: " +android.getVer() + "\n" +  android.getApi(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(androids != null) {
            return androids.size();
        }else {
            return 0;
        }
    }

    public class AndroidHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_version,tv_api_level;
        CardView cardView;
        public AndroidHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_version = itemView.findViewById(R.id.tv_version);
            tv_api_level = itemView.findViewById(R.id.tv_api_level);
            cardView = itemView.findViewById(R.id.cvAndroid);
        }
    }
}

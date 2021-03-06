package com.example.rxandroidretrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdapterAndroid extends RecyclerView.Adapter<AdapterAndroid.AndroidHolder> {

    Context context;
    List<Android> androids = null;
    //TextView tv_name,tv_version,tv_api_level;


    public AdapterAndroid(Context context, List<Android> androids) {
        this.context = context;
        this.androids = androids;
    }

    public AdapterAndroid() {
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
        Android android = androids.get(i);
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
        if(androids == null){
            return 0;
        }else {
            return androids.size();
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
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}

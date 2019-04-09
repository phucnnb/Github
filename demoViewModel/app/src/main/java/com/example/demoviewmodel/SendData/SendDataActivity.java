package com.example.demoviewmodel.SendData;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.demoviewmodel.R;

public class SendDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);

        FragmentA fragmentA = new FragmentA();
        FragmentB fragmentB = new FragmentB();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_a,fragmentA)
                .add(R.id.container_b,fragmentB)
                .commit();
    }
}

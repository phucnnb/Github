package com.example.demoviewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_A_1)
    Button btn_A_1;

    @BindView(R.id.btn_A_2)
    Button btn_A_2;

    @BindView(R.id.btn_A_3)
    Button btn_A_3;

    @BindView(R.id.btn_B_1)
    Button btn_B_1;

    @BindView(R.id.btn_B_2)
    Button btn_B_2;

    @BindView(R.id.btn_B_3)
    Button btn_B_3;

    @BindView(R.id.txtTeamA)
    TextView txtTeamA;

    @BindView(R.id.txtTeamB)
    TextView txtTeamB;


    CountNumberViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewModel = ViewModelProviders.of(this).get(CountNumberViewModel.class);
        registerLiveDataListenner();
        Event();
    }

    private void Event() {
        btn_A_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.increaseScoreTeamA(1);
            }
        });

        btn_A_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.increaseScoreTeamA(2);
            }
        });

        btn_A_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.increaseScoreTeamA(3);
            }
        });

        btn_B_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.increaseScoreTeamB(1);
            }
        });

        btn_B_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.increaseScoreTeamB(2);
            }
        });

        btn_B_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.increaseScoreTeamB(3);
            }
        });
    }

    private void registerLiveDataListenner() {
        mViewModel.getmScoreTeamA().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                txtTeamA.setText(String.valueOf(integer));
            }
        });

        mViewModel.getmScoreTeamB().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                txtTeamB.setText(String.valueOf(integer));
            }
        });
    }
}

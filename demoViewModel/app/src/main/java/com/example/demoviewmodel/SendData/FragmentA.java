package com.example.demoviewmodel.SendData;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.demoviewmodel.R;


public class FragmentA extends Fragment {
    private EditText editA;
    private Button btn_A;
    private ShareViewModel shareViewModel;
    private int i = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_a,container,false);

        editA = v.findViewById(R.id.editA);
        btn_A = v.findViewById(R.id.btn_A);

        btn_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareViewModel.setCheck(1);
                shareViewModel.setText(editA.getText());
                editA.setText("");
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shareViewModel = ViewModelProviders.of(getActivity()).get(ShareViewModel.class);
        shareViewModel.getCheck().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Log.d("AAAA",integer + " AAAAAAAAAAA");
                i = integer;
                if(i == 2){
                    shareViewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
                        @Override
                        public void onChanged(@Nullable CharSequence charSequence) {
                            if (charSequence.length() != 0){
                                editA.setText(charSequence);
                            }
                        }
                    });
                }
            }
        });

    }
}

package com.example.demoviewmodel.SendData;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.database.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.demoviewmodel.R;

public class FragmentB extends Fragment {

    private EditText editB;
    private Button btnB;
    private ShareViewModel shareViewModel;
    private int i = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b,container,false);
        editB = v.findViewById(R.id.editB);
        btnB = v.findViewById(R.id.btn_B);

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareViewModel.setCheck(2);
                shareViewModel.setText(editB.getText());
                editB.setText("");
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
                i = integer;
                if(i == 1){
                    shareViewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
                        @Override
                        public void onChanged(@Nullable CharSequence charSequence) {
                            if (charSequence.length() != 0){
                                editB.setText(charSequence);
                            }

                        }
                    });
                }
            }
        });


    }
}

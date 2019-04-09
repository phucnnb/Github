package com.example.demoviewmodel.SendData;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {
    private MutableLiveData<CharSequence> text = new MutableLiveData<>();
    private MutableLiveData<Integer> check = new MutableLiveData<>();


    public void setText(CharSequence input){
        text.setValue(input);
    }

    public void setCheck(Integer input){
        check.setValue(input);
    }

    public MutableLiveData<CharSequence> getText(){
        return text;
    }

    public MutableLiveData<Integer> getCheck(){
        return check;
    }

}

package com.example.demorxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Main3Activity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        DisposableObserver<Notes> disposableObserver = new DisposableObserver<Notes>() {
            @Override
            public void onNext(Notes notes) {
                Log.d("Note","Id: " + notes.getId() + "----" + "Note: " + notes.getNote());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        final List<Notes> listNotes = prepareNotes();
        Observable<Notes> listQuanSat = Observable.create(new ObservableOnSubscribe<Notes>() {
            @Override
            public void subscribe(ObservableEmitter<Notes> emitter) throws Exception {
                for(Notes note : listNotes){
                    if(!emitter.isDisposed()){

                        emitter.onNext(note);
                        Log.d("kiemtra",emitter.isDisposed() + "");
                    }
                }

                if(!emitter.isDisposed()){
                    emitter.onComplete();
                    Log.d("kiemtra1",emitter.toString());

                }

            }
        });


        compositeDisposable.add(listQuanSat.observeOn(Schedulers.io())
                                           .subscribeOn(AndroidSchedulers.mainThread())
                                           .map(new Function<Notes, Notes>() {
                                               @Override
                                               public Notes apply(Notes notes) throws Exception {
                                                   notes.setNote(notes.getNote().toUpperCase());
                                                   return notes;
                                               }
                                           })
                                           .subscribeWith(disposableObserver) );


    }

    private List<Notes> prepareNotes() {
        List<Notes> notes = new ArrayList<>();
        notes.add(new Notes(1, "buy tooth paste!"));
        notes.add(new Notes(2, "call brother!"));
        notes.add(new Notes(3, "watch narcos tonight!"));
        notes.add(new Notes(4, "pay power bill!"));

        return notes;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}

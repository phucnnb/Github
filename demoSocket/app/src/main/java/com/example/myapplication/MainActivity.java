package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnAdd)
    Button btnAdd;

    @BindView(R.id.btnSend)
    Button btnSend;

    @BindView(R.id.editSend)
    EditText editSend;

    @BindView(R.id.recyclerView)
    ListView lvNoiDung;

    @BindView(R.id.lvUser)
    ListView lvUser;

    private Socket socket;
    private ArrayAdapter adapterUser, adapterNoiDung;
    private ArrayList<String> arrayUser, arrayNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        try {
            socket = IO.socket("http://192.168.1.44:3000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.d("AAA","AAAAAAAAAAAAAAAAAAAA");

            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        });
        socket.connect();

        socket.on("server-send-result",onRetrieveResult);
        socket.on("server-send-user",onListUser);
        socket.on("server-send-chat",onListChat);

        arrayUser = new ArrayList<>();
        adapterUser = new ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayUser);
        lvUser.setAdapter(adapterUser);

        arrayNoiDung = new ArrayList<>();
        adapterNoiDung = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayNoiDung);
        lvNoiDung.setAdapter(adapterNoiDung);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editSend.getText().toString().trim().length() > 0){
                    Log.d("BBB",editSend.getText().toString());
                    socket.emit("client-send-chat",editSend.getText().toString());
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editSend.getText().toString().trim().length() > 0){
                    Log.d("BBB",editSend.getText().toString());
                    socket.emit("client-register-user",editSend.getText().toString());
                }
            }
        });
    }

    private Emitter.Listener onListChat = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        String noiDung = object.getString("chatConent");
                        arrayNoiDung.add(noiDung);
                        adapterNoiDung.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private Emitter.Listener onListUser = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];

                    try {
                        JSONArray array = object.getJSONArray("danhsach");
                        arrayUser.clear();
                        for (int i = 0; i < array.length(); i++){
                            String username = array.getString(i);
                            arrayUser.add(username);
                            adapterUser.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(),username,Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private Emitter.Listener onRetrieveResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        boolean exits = object.getBoolean("ketqua");

                        if(exits){
                            Toast.makeText(getApplicationContext(),"Đã Tồn Tại!!!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"Đăng Kí Thành Công!",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
}

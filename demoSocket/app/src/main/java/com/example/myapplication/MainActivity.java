package com.example.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    RecyclerView recyclerView;

    @BindView(R.id.lvUser)
    ListView lvUser;

    private Socket socket;
    private ArrayAdapter<String> adapterUser;
    private ArrayList<String> arrayUser;
    private AdapterChat adapterChat;
    private List<Chat> listChat;

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
        adapterUser = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayUser);
        lvUser.setAdapter(adapterUser);

        listChat = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterChat = new AdapterChat(MainActivity.this,listChat);
        recyclerView.setAdapter(adapterChat);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editSend.getText().toString().trim().length() > 0){
                    Calendar c = Calendar.getInstance();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
                    String datetime = dateformat.format(c.getTime());
                    socket.emit("client-send-time",datetime);
                    socket.emit("client-send-chat",editSend.getText().toString());

                    editSend.setText("");

                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editSend.getText().toString().trim().length() > 0){
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
                    Log.d("EEE",object.toString());
                    try {
                        String user = object.getString("user");
                        String mess = object.getString("chatConent");
                        String date = object.getString("time");
                        Log.d("AAAA",user + "   " + mess+ "    " +date);
                        Chat chat = new Chat(user,mess,date);
                        listChat.add(chat);
                        adapterChat.notifyDataSetChanged();
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

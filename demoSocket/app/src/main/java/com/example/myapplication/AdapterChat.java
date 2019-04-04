package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.ChatHolder> {

    Context context;
    List<Chat> chats;

    public AdapterChat(Context context, List<Chat> chats) {
        this.context = context;
        this.chats = chats;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.item_chat,viewGroup,false);
        ChatHolder chatHolder = new ChatHolder(itemView);
        return chatHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder chatHolder, int i) {
        Chat chat = chats.get(i);
        chatHolder.tvUser.setText(chat.getUser());
        chatHolder.tvChat.setText(chat.getMessage());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder {
        TextView tvUser,tvChat;
        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvChat = itemView.findViewById(R.id.tvChat);
        }
    }
}

package com.example.allinoneapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinoneapplication.R;
import com.example.allinoneapplication.model.Chat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<Chat> chatList;
    int u_id, d_id, chat_check;


    public ChatAdapter(List<Chat> chatList, int u_id, int d_id, int chat_check) {
        this.chatList = chatList;
        this.u_id = u_id;
        this.d_id = d_id;
        this.chat_check = chat_check;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        int Sender_ID = chatList.get(position).getSender_id();
        int Receiver_ID = chatList.get(position).getReceiver_id();
        String type = chatList.get(position).getSender_type();

        if (chat_check == 1) {
            if (type.equals("p")) {
                holder.tv_receiver_message.setVisibility(View.GONE);
                holder.tv_sender_message.setVisibility(View.GONE);
                if (Sender_ID == u_id && Receiver_ID == d_id) {
                    holder.tv_sender_message.setVisibility(View.VISIBLE);
                    holder.tv_sender_message.setText(chatList.get(position).getChat_message());
                    holder.tv_sender_message_time.setText(chatList.get(position).getChat_datetime());
                }
            } else if (type.equals("d")) {
                holder.tv_sender_message.setVisibility(View.GONE);
                holder.tv_receiver_message.setVisibility(View.GONE);
                if (Receiver_ID == u_id && Sender_ID == d_id) {
                    holder.tv_receiver_message.setVisibility(View.VISIBLE);
                    holder.tv_receiver_message.setText(chatList.get(position).getChat_message());
                    holder.tv_receiver_message_time.setText(chatList.get(position).getChat_datetime());
                }
            }
        } else if (chat_check == 2) {
            if (type.equals("d")) {
                holder.tv_receiver_message.setVisibility(View.GONE);
                holder.tv_sender_message.setVisibility(View.GONE);
                if (Sender_ID == d_id && Receiver_ID == u_id) {
                    holder.tv_sender_message.setVisibility(View.VISIBLE);
                    holder.tv_sender_message.setText(chatList.get(position).getChat_message());
                    holder.tv_sender_message_time.setText(chatList.get(position).getChat_datetime());
                }
            } else if (type.equals("p")) {
                holder.tv_sender_message.setVisibility(View.GONE);
                holder.tv_receiver_message.setVisibility(View.GONE);
                if (Receiver_ID == d_id && Sender_ID == u_id) {
                    holder.tv_receiver_message.setVisibility(View.VISIBLE);
                    holder.tv_receiver_message.setText(chatList.get(position).getChat_message());
                    holder.tv_receiver_message_time.setText(chatList.get(position).getChat_datetime());
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView tv_receiver_message, tv_sender_message, tv_receiver_message_time, tv_sender_message_time;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_receiver_message = itemView.findViewById(R.id.tv_receiver_message);
            tv_sender_message = itemView.findViewById(R.id.tv_sender_message);
            tv_receiver_message_time = itemView.findViewById(R.id.tv_receiver_message_time);
            tv_sender_message_time = itemView.findViewById(R.id.tv_sender_message_time);
        }
    }
}


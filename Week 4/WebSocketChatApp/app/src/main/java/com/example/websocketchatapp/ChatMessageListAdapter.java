package com.example.websocketchatapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ChatMessageListAdapter extends ArrayAdapter<ChatMessage> {

    private Context mContext;
    private ArrayList<ChatMessage> messages;

    public ChatMessageListAdapter(@NonNull Context context, @LayoutRes ArrayList<ChatMessage> list) {
        super(context,0,  list);
        this.mContext = context;
        this.messages = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        ChatMessage currentMessage = messages.get(position);

        TextView nameTextView = (TextView) listItem.findViewById(R.id.chatItem);
        String message = currentMessage.getMessage();
        nameTextView.setText(message);

        if (currentMessage.getType() == ChatMessage.MessageType.SENT) {
            nameTextView.setTextColor(Color.BLACK);
            nameTextView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (currentMessage.getType() == ChatMessage.MessageType.RECEIVED) {
            nameTextView.setTextColor(Color.BLACK);
            nameTextView.setTypeface(Typeface.DEFAULT);
        }
        if (currentMessage.getType() == ChatMessage.MessageType.SYSTEM) {
            nameTextView.setTextColor(Color.BLUE);
            nameTextView.setTypeface(Typeface.MONOSPACE);
        }

        return listItem;
    }

    public ArrayList<ChatMessage> getMessages() {
        return this.messages;
    }
}

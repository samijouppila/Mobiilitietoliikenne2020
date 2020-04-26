package com.example.websocketchatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ChatClientInterface{
    private ChatClient client;
    private ArrayList<ChatMessage> messages;
    private ChatMessageListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openChatConnection();
        messages = new ArrayList<>();
        adapter = new ChatMessageListAdapter(this,messages);
        ListView listView = findViewById(R.id.listChatMessages);
        listView.setAdapter(adapter);

        findViewById(R.id.buttonSendMessage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (client != null && client.isOpen()) {
            TextView messageField = findViewById(R.id.textMessageField);
            String message = messageField.getText().toString();
            client.send(message);
            //messages.add(new ChatMessage(message, ChatMessage.MessageType.SENT));
            //adapter.notifyDataSetChanged();
        } else {
            openChatConnection();
        }
    }

    public void openChatConnection() {
        try {
            client = new ChatClient(new URI("wss://obscure-waters-98157.herokuapp.com"),this);
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListView listView = findViewById(R.id.listChatMessages);
                ChatMessageListAdapter adapter = (ChatMessageListAdapter) listView.getAdapter();
                adapter.getMessages().add(new ChatMessage(message, ChatMessage.MessageType.RECEIVED));
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSystemMessage(final String systemMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListView listView = findViewById(R.id.listChatMessages);
                ChatMessageListAdapter adapter = (ChatMessageListAdapter) listView.getAdapter();
                adapter.getMessages().add(new ChatMessage(systemMessage, ChatMessage.MessageType.SYSTEM));
                adapter.notifyDataSetChanged();
            }
        });
    }
}

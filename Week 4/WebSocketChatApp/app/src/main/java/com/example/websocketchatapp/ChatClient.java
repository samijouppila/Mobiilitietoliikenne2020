package com.example.websocketchatapp;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

interface ChatClientInterface {
    void onMessage(String message);
    void onSystemMessage(String systemMessage);
}

public class ChatClient extends WebSocketClient {

    ChatClientInterface observer;

    public ChatClient(URI serverUri, ChatClientInterface observer) {
        super(serverUri);
        this.observer = observer;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.d("wsclient","onOpen called");
        observer.onSystemMessage("Connected");
    }

    @Override
    public void onMessage(String message) {
        Log.d("wsclient","onMessage called");
        observer.onMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.d("wsclient","onClose called");
        observer.onSystemMessage("Disconnected. Send new message to try to reconnect.");
    }

    @Override
    public void onError(Exception ex) {
        Log.d("wsclient", "onError called");
        observer.onSystemMessage("Unexpected error occurred: " + ex.toString());
    }
}

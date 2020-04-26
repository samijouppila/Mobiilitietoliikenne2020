package com.example.websocketchatapp;


public class ChatMessage {

    enum MessageType {
        SYSTEM,
        RECEIVED,
        SENT
    }

    private String message;
    private MessageType type;

    public ChatMessage(String message, MessageType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getType() {
        return type;
    }
}

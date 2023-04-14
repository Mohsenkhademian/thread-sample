package org.example;

public class Message {
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public synchronized String getMessage() {
        return message;
    }

    public synchronized void setMessage(String message) {
        this.message = message;
    }
}

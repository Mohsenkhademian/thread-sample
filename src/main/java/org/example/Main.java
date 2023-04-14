package org.example;

public class Main {
    public static void main(String[] args) {

        Message message = new Message("");
        Sender sender = new Sender(message);
        Receiver receiver = new Receiver(message);
        sender.start();
        receiver.start();
    }
}
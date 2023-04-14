package org.example;

public class Sender extends Thread {
    private Message message;

    public Sender(Message message) {
        this.message = message;
    }

    public void run() {
        String[] messages = {"Hello", "Mohsen", "Goodbye"};
        for (int i = 0; i < messages.length; i++) {
            message.setMessage(messages[i]);
            synchronized (message) {
                message.notifyAll();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
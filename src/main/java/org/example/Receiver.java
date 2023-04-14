package org.example;

public class Receiver implements Runnable {
    private Message message;

    public Receiver(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (message) {
                if (message.getMessage().isEmpty()) {
                    try {
                        message.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Received message: " + message.getMessage());
                message.setMessage("");
            }
        }
    }
}
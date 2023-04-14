package org.example;

public class Sender implements Runnable {
    private Message message;
    private boolean isPaused = false;

    public Sender(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (!isPaused) {
                synchronized (message) {
                    if (message.getMessage().isEmpty()) {
                        try {
                            message.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("Sent message: " + message.getMessage());
                    message.setMessage("");
                }
            }
        }
    }

    public synchronized void sendMessage(String message) {
        this.message.setMessage(message);
        synchronized (this.message) {
            this.message.notifyAll();
        }
    }

    public synchronized void pauseSending() {
        isPaused = true;
    }

    public synchronized void resumeSending() {
        isPaused = false;
        synchronized (this.message) {
            this.message.notifyAll();
        }
    }

    public Message getMessage() {
        return message;
    }

    public boolean isPaused() {
        return isPaused;
    }

}
package org.example;

public class Receiver extends Thread{
    private Message message;

    public Receiver(Message message) {
        this.message = message;
    }

    public void run() {
        while (true) {
            synchronized (message) {
                if (message.getMessage().isEmpty()) {
                    try {
                        message.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Receive Message : " + message.getMessage());
                message.setMessage("");
            }
        }
    }
}

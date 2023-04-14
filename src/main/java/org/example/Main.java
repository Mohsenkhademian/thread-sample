package org.example;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Message message = new Message("");
        Sender sender = new Sender(message);
        Receiver receiver = new Receiver(message);
        int maxMessages = 5;
        final boolean pauseFlag = false;
        Timer timer = new Timer();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(sender);
        executorService.submit(receiver);

        timer.scheduleAtFixedRate(new TimerTask() {
            final int maxMessages = 5;
            int count = 0;

            @Override
            public void run() {
                if (!pauseFlag && count < maxMessages) {
                    count++;
                    sender.sendMessage("Message " + count);
                }
                if (count == maxMessages) {
                    timer.cancel();
                    executorService.shutdown();
                }
            }
        }, 0, 2000);

        // Pause and resume methods for Sender
        sender.pauseSending();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sender.resumeSending();
    }

}

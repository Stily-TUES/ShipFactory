package org.example;
public class Clock extends Thread {
    private int hours = 0;
    private int days = 0;
    private boolean running = true;
    private final ResourceManager resourceManager;

    public Clock(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(3000); // 3 seconds for an hour
                hours++;
                if (hours % 24 == 0) {
                    days++;
                    System.out.println("Day " + days + " passed.");
                    resourceManager.printResources();
                }
                System.out.println("Hour passed: " + hours);
                synchronized (this) {
                    notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopClock() {
        running = false;
    }
}
package org.example;
abstract class FactoryWorker extends Thread {
    protected int output;
    protected int totalProduced = 0;
    protected Clock clock;
    protected ResourceManager resourceManager;
    protected boolean running = true;

    public FactoryWorker(int output, Clock clock, ResourceManager resourceManager) {
        this.output = output;
        this.clock = clock;
        this.resourceManager = resourceManager;
    }

    public void run() {
        while (running) {
            synchronized (clock) {
                try {
                    clock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                produce();
            }
        }
    }

    protected abstract void produce();

    public int getTotalProduced() {
        return totalProduced;
    }

    public void stopWorker() {
        running = false;
    }
    public void increaseOutputByPercentage(int percentage) {
        output += output * percentage / 100;
    }

    public int getOutput() {
        return output;
    }
}
package org.example;

public abstract class FactoryWorker extends Thread {
    protected int output;
    protected int totalProduced = 0;
    protected Clock clock;
    protected ResourceManager resourceManager;
    protected static final int MAX_ACTIVE = 5; // Maximum active workers
    protected static java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(MAX_ACTIVE);

    public FactoryWorker(int output, Clock clock, ResourceManager resourceManager) {
        this.output = output;
        this.clock = clock;
        this.resourceManager = resourceManager;
    }

    public void run() {
        while (!Thread.interrupted()) {
            try {
                semaphore.acquire();
                synchronized (clock) {
                    clock.wait();
                }
                produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        }
    }

    protected abstract void produce();

    public void increaseOutputByPercentage(int percentage) {
        output += output * percentage / 100;
    }

    public int getOutput() {
        return output;
    }
}

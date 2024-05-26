package org.example;

public class Mission {
    private final int shipsSent;
    private final long startTime;

    public Mission(int shipsSent) {
        this.shipsSent = shipsSent;
        this.startTime = System.currentTimeMillis();
    }

    public int getShipsSent() {
        return shipsSent;
    }

    public long getStartTime() {
        return startTime;
    }
}

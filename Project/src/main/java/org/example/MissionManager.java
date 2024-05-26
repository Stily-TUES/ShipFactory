package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MissionManager extends Thread {
    private final ResourceManager resourceManager;
    private final List<Mission> missions = new ArrayList<>();
    private final long MISSION_DURATION = 24 * 1000; // some random in game time for mission to complete

    public MissionManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public synchronized void addMission(int shipsSent) {
        missions.add(new Mission(shipsSent));
    }

    public void run() {
        while (true) {
            synchronized (this) {
                long currentTime = System.currentTimeMillis();
                List<Mission> completedMissions = new ArrayList<>();

                for (Mission mission : missions) {
                    if (currentTime - mission.getStartTime() >= MISSION_DURATION) {
                        completedMissions.add(mission);
                    }
                }

                for (Mission mission : completedMissions) {
                    processMissionResult(mission);
                    missions.remove(mission);
                }
            }

            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processMissionResult(Mission mission) {
        int shipsSent = mission.getShipsSent();
        double successRate = Math.min(0.05 * shipsSent, 0.9); // 5% per ship max 90% so there is a gambling element XD
        boolean missionSuccessful = new Random().nextDouble() < successRate;

        if (missionSuccessful) {
            int goldEarned = 10 + new Random().nextInt(41); // you get random amount of gold between 10 and 50
            resourceManager.addGold(goldEarned);
            System.out.println("--------------------------Mission successful! Earned " + goldEarned + " gold.--------------------");
        } else {
            System.out.println("--------------------------Mission failed. Better luck next time!---------------------------------");
        }
    }
}

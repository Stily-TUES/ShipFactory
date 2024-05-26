package org.example;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ResourceManager resourceManager = new ResourceManager();
    private static final Clock clock = new Clock(resourceManager);
    private static final MissionManager missionManager = new MissionManager(resourceManager);

    private static int woodFactoryCount = 1;
    private static int steelFactoryCount = 1;
    private static int clothFactoryCount = 1;

    private static int woodUpgradeCost = 20;
    private static int steelUpgradeCost = 20;
    private static int clothUpgradeCost = 20;

    private static final WoodFactoryWorker woodWorker = new WoodFactoryWorker(100, clock, resourceManager);
    private static final SteelFactoryWorker steelWorker = new SteelFactoryWorker(50, clock, resourceManager);
    private static final ClothFactoryWorker clothWorker = new ClothFactoryWorker(50, clock, resourceManager);


    public static void main(String[] args) {
        clock.start();
        woodWorker.start();
        steelWorker.start();
        clothWorker.start();
        missionManager.start();

        new Thread(Main::handleUserInput).start();
    }

    private static void handleUserInput() {
        while (true) {
            System.out.println("Enter command (build, upgrade, resources, send, quit):");
            String command = scanner.nextLine();
            switch (command) {
                case "build":
                    buildFactory();
                    break;
                case "upgrade":
                    upgradeFactory();
                    break;
                case "resources":
                    resourceManager.printResources();
                    break;
                case "send":
                    sendShipsOnMission();
                    break;
                case "quit":
                    clock.stopClock();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }

    private static void buildFactory() {
        System.out.println("Which factory to build? (wood, steel, cloth, ship)");
        String factoryType = scanner.nextLine();
        if (resourceManager.consumeResources(10, 5, 5)) {
            switch (factoryType) {
                case "wood":
                    woodFactoryCount++;
                    new WoodFactoryWorker(10, clock, resourceManager).start();
                    break;
                case "steel":
                    steelFactoryCount++;
                    new SteelFactoryWorker(5, clock, resourceManager).start();
                    break;
                case "cloth":
                    clothFactoryCount++;
                    new ClothFactoryWorker(5, clock, resourceManager).start();
                    break;
                case "ship":
                    new ShipFactoryWorker(1, clock, resourceManager).start();
                    break;
                default:
                    System.out.println("Unknown factory type");
                    break;
            }
            System.out.println(factoryType + " factory built.");
        } else {
            System.out.println("Not enough resources to build a factory.");
        }
    }

    private static void upgradeFactory() {
        System.out.println("Which factory to upgrade? (wood, steel, cloth)");
        String factoryType = scanner.nextLine();
        switch (factoryType) {
            case "wood":
                if (resourceManager.consumeResources(woodUpgradeCost, woodUpgradeCost, woodUpgradeCost)) {
                    woodWorker.increaseOutputByPercentage(20);
                    woodUpgradeCost *= 1.5;
                    System.out.println("Wood factory upgraded. New output: " + woodWorker.getOutput());
                    System.out.println("New upgrade cost: " + woodUpgradeCost);
                } else {
                    System.out.println("Not enough resources to upgrade the factory.");
                }
                break;
            case "steel":
                if (resourceManager.consumeResources(steelUpgradeCost, steelUpgradeCost, steelUpgradeCost)) {
                    steelWorker.increaseOutputByPercentage(20);
                    steelUpgradeCost *= 1.5;
                    System.out.println("Steel factory upgraded. New output: " + steelWorker.getOutput());
                    System.out.println("New upgrade cost: " + steelUpgradeCost);
                } else {
                    System.out.println("Not enough resources to upgrade the factory.");
                }
                break;
            case "cloth":
                if (resourceManager.consumeResources(clothUpgradeCost, clothUpgradeCost, clothUpgradeCost)) {
                    clothWorker.increaseOutputByPercentage(20);
                    clothUpgradeCost *= 1.5;
                    System.out.println("Cloth factory upgraded. New output: " + clothWorker.getOutput());
                    System.out.println("New upgrade cost: " + clothUpgradeCost);
                } else {
                    System.out.println("Not enough resources to upgrade the factory.");
                }
                break;
            default:
                System.out.println("Unknown factory type.");
                break;
        }
    }

    private static void sendShipsOnMission() {
        System.out.println("Enter the number of ships to send on the mission (max 20):");
        int numShips = scanner.nextInt();
        scanner.nextLine();

        if (numShips > 20) {
            System.out.println("You can only send a maximum of 20 ships.");
            return;
        }

        if (!resourceManager.hasEnoughShips(numShips)) {
            System.out.println("Not enough ships available.");
            return;
        }

        resourceManager.removeShips(numShips);
        missionManager.addMission(numShips);
        System.out.println("Mission started with " + numShips + " ships. You will get the result in a couple of hours.");
    }
}

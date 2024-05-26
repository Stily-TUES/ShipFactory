package org.example;

public class ResourceManager {
    private int wood = 0;
    private int steel = 0;
    private int cloth = 0;
    private int gold = 0;
    private int ships = 0;

    public synchronized void addWood(int amount) {
        wood += amount;
    }
    public synchronized void addGold(int amount) {
        gold += amount;
    }

    public synchronized void addSteel(int amount) {
        steel += amount;
    }

    public synchronized void addCloth(int amount) {
        cloth += amount;
    }
    public synchronized void addShips(int amount) {
        ships += amount;
    }

    public synchronized void removeShips(int amount) {
        ships -= amount;
    }
    public synchronized boolean hasEnoughShips(int amount) {
        return ships >= amount;
    }

    public synchronized boolean consumeResources(int woodAmount, int steelAmount, int clothAmount) {
        if (wood >= woodAmount && steel >= steelAmount && cloth >= clothAmount) {
            wood -= woodAmount;
            steel -= steelAmount;
            cloth -= clothAmount;
            return true;
        }
        return false;
    }

    public synchronized void printResources() {
        System.out.println("Resources - Wood: " + wood + ", Steel: " + steel + ", Cloth: " + cloth + ", Gold: " + gold + ", Ships: " + ships);
    }
}

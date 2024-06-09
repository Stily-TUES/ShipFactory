package org.example;

import java.util.concurrent.Semaphore;

public class ResourceManager {
    private int wood = 0;
    private int steel = 0;
    private int cloth = 0;
    private int gold = 0;
    private int ships = 0;

    private final Semaphore woodSemaphore = new Semaphore(1);
    private final Semaphore steelSemaphore = new Semaphore(1);
    private final Semaphore clothSemaphore = new Semaphore(1);
    private final Semaphore goldSemaphore = new Semaphore(1);
    private final Semaphore shipsSemaphore = new Semaphore(1);

    // Add methods with semaphore protection
    public void addWood(int amount) {
        try {
            woodSemaphore.acquire();
            wood += amount;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Handle thread interruption
        } finally {
            woodSemaphore.release();
        }
    }

    public void addSteel(int amount) {
        try {
            steelSemaphore.acquire();
            steel += amount;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            steelSemaphore.release();
        }
    }

    public void addCloth(int amount) {
        try {
            clothSemaphore.acquire();
            cloth += amount;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            clothSemaphore.release();
        }
    }

    public void addGold(int amount) {
        try {
            goldSemaphore.acquire();
            gold += amount;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            goldSemaphore.release();
        }
    }

    public void addShips(int amount) {
        try {
            shipsSemaphore.acquire();
            ships += amount;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            shipsSemaphore.release();
        }
    }

    public void removeShips(int amount) {
        try {
            shipsSemaphore.acquire();
            if (ships >= amount) {
                ships -= amount;
            } else {
                throw new IllegalStateException("Attempting to remove more ships than available");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            shipsSemaphore.release();
        }
    }

    public boolean hasEnoughShips(int amount) {
        try {
            shipsSemaphore.acquire();
            return ships >= amount;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            shipsSemaphore.release();
        }
    }

    public boolean consumeResources(int woodAmount, int steelAmount, int clothAmount) {
        boolean acquired = false;
        try {
            if (woodSemaphore.tryAcquire() && steelSemaphore.tryAcquire() && clothSemaphore.tryAcquire()) {
                acquired = true;
                if (wood >= woodAmount && steel >= steelAmount && cloth >= clothAmount) {
                    wood -= woodAmount;
                    steel -= steelAmount;
                    cloth -= clothAmount;
                    return true;
                }
                return false;
            } else {
                return false;
            }
        } finally {
            if (acquired) {
                woodSemaphore.release();
                steelSemaphore.release();
                clothSemaphore.release();
            }
        }
    }

    // Print current resource levels
    public void printResources() {
        System.out.println("Resources - Wood: " + wood + ", Steel: " + steel + ", Cloth: " + cloth + ", Gold: " + gold + ", Ships: " + ships);
    }
}

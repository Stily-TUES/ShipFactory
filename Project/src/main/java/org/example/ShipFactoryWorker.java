package org.example;

public class ShipFactoryWorker extends FactoryWorker {
    public ShipFactoryWorker(int output, Clock clock, ResourceManager resourceManager) {
        super(output, clock, resourceManager);
    }

    @Override
    protected void produce() {
        if (resourceManager.consumeResources(50, 25, 25)) {
            resourceManager.addShips(output);
            totalProduced += output;
            System.out.println("Ships produced: " + totalProduced);
        } else {
            System.out.println("Not enough resources to build ships");
        }
    }
}
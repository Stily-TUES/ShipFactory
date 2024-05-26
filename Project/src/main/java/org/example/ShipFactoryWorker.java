package org.example;

class ShipFactoryWorker extends FactoryWorker {
    public ShipFactoryWorker(int output, Clock clock, ResourceManager resourceManager) {
        super(output, clock, resourceManager);
    }

    @Override
    protected void produce() {
        if (resourceManager.consumeResources(50, 25, 25)) {
            totalProduced += output;
            resourceManager.addShips(output);
            System.out.println("Ships produced: " + totalProduced);
        } else {
            System.out.println("Not enough resources to build ships");
        }
    }
}
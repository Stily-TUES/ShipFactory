package org.example;

public class WoodFactoryWorker extends FactoryWorker {
    public WoodFactoryWorker(int output, Clock clock, ResourceManager resourceManager) {
        super(output, clock, resourceManager);
    }

    @Override
    protected void produce() {
        resourceManager.addWood(output);
        totalProduced += output;
        System.out.println("Wood produced: " + totalProduced);
    }
}
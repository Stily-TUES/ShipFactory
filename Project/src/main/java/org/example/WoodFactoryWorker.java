package org.example;

class WoodFactoryWorker extends FactoryWorker {
    public WoodFactoryWorker(int output, Clock clock, ResourceManager resourceManager) {
        super(output, clock, resourceManager);
    }

    @Override
    protected void produce() {
        totalProduced += output;
        resourceManager.addWood(output);
        System.out.println("Wood produced: " + totalProduced + " its making: " + output + " wood per hour.");
    }
}
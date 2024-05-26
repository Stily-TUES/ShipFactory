package org.example;

class ClothFactoryWorker extends FactoryWorker {
    public ClothFactoryWorker(int output, Clock clock, ResourceManager resourceManager) {
        super(output, clock, resourceManager);
    }

    @Override
    protected void produce() {
        totalProduced += output;
        resourceManager.addCloth(output);
        System.out.println("Cloth produced: " + totalProduced + " its making: " + output + " cloth per hour.");
    }
}
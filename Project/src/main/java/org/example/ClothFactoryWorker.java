package org.example;

public class ClothFactoryWorker extends FactoryWorker {
    public ClothFactoryWorker(int output, Clock clock, ResourceManager resourceManager) {
        super(output, clock, resourceManager);
    }

    @Override
    protected void produce() {
        resourceManager.addCloth(output);
        totalProduced += output;
        System.out.println("Cloth produced: " + totalProduced);
    }
}

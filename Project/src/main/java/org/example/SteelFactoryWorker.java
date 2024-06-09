package org.example;

public class SteelFactoryWorker extends FactoryWorker {
    public SteelFactoryWorker(int output, Clock clock, ResourceManager resourceManager) {
        super(output, clock, resourceManager);
    }

    @Override
    protected void produce() {
        resourceManager.addSteel(output);
        totalProduced += output;
        System.out.println("Steel produced: " + totalProduced);
    }
}
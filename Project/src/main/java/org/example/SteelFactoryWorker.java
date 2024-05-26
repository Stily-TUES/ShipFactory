package org.example;

class SteelFactoryWorker extends FactoryWorker {
    public SteelFactoryWorker(int output, Clock clock, ResourceManager resourceManager) {
        super(output, clock, resourceManager);
    }

    @Override
    protected void produce() {
        totalProduced += output;
        resourceManager.addSteel(output);
        System.out.println("Steel produced: " + totalProduced + " its making: " + output + " steel per hour.");
    }
}
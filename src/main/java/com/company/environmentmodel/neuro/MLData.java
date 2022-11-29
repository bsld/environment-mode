package com.company.environmentmodel.neuro;

// MLData class holds all the input values and target values.
public class MLData {

    private double[] inputs;
    private double[] targets;

    public void setTargets(double[] targets) {
        this.targets = targets;
    }

    public MLData(double[] inputs, double[] targets) {
        this.inputs = inputs;
        this.targets = targets;
    }
    
    public double[] getInputs() {
        return inputs;
    }
    
    public double[] getTargets() {
        return targets;
    }
}

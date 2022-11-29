package com.company.environmentmodel.neuro;

import java.util.ArrayList;
import java.util.List;

// MLDataSet class holds all the input values and target values List.
public class MLDataSet {

    private double[][] inputs;
    private double[][] targets;
    private List<MLData> data;

    public MLDataSet() {
        data = new ArrayList<>();
    }

    public MLDataSet(double[][] inputs, double[][] targets) {
        this.data = new ArrayList<>();
        this.inputs = inputs;
        this.targets = targets;
        for (int i = 0; i < this.inputs.length; i++) {
            this.data.add(new MLData(inputs[i], targets[i]));
        }
    }
    
    public List<MLData> getData() {
        return data;
    }
    
    public void addMLData(MLData mlData) {
        this.data.add(mlData);
    }
}

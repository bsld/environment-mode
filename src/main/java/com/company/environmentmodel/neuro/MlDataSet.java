package com.company.environmentmodel.neuro;

import java.util.ArrayList;
import java.util.List;

import com.company.environmentmodel.environment.member.animal.brain.Input;
import com.company.environmentmodel.environment.member.animal.brain.Output;

// MLDataSet class holds all the input values and target values List.
public class MlDataSet {

    private double[][] inputs;
    private double[][] targets;
    private List<MlData> data;

    public MlDataSet() {
        data = new ArrayList<>();
    }

    public MlDataSet(double[][] inputs, double[][] targets) {
        this.data = new ArrayList<>();
        this.inputs = inputs;
        this.targets = targets;
        for (int i = 0; i < this.inputs.length; i++) {
            this.data.add(new MlData(this.inputs[i], this.targets[i]));
        }
    }
    
    public MlDataSet(ArrayList<Input> inputs, ArrayList<Output> targets) {
        this.data = new ArrayList<>();
        this.inputs = new double[inputs.size()][];
        this.targets = new double[targets.size()][];
        
        for (int i = 0; i < inputs.size(); i++) {
            this.inputs[i] = inputs.get(i).toArray();
            this.targets[i] = targets.get(i).toArray();
        }
        
        for (int i = 0; i < this.inputs.length; i++) {
            this.data.add(new MlData(this.inputs[i], this.targets[i]));
        }
    }

    public List<MlData> getData() {
        return data;
    }

    public void addMLData(MlData mlData) {
        this.data.add(mlData);
    }
}

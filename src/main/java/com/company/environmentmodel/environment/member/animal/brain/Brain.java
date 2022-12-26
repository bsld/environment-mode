package com.company.environmentmodel.environment.member.animal.brain;

import com.company.environmentmodel.neuro.Functions;
import com.company.environmentmodel.neuro.MlDataSet;
import com.company.environmentmodel.neuro.NeuralNetwork;

public abstract class Brain implements Cloneable {
    protected NeuralNetwork net;

    protected Brain() {
        net = new NeuralNetwork(Input.getSize(), 13, Output.getSize());
        net.init();
        net.setLearningRate(0.01);
        net.setMomentum(0.5);
        // net.setActivationFunction(ActivationFunction.leakyReLu());
        net.setActivationFunction(Functions.sigmoid());
        train();
    }

    void train() {
        this.net.train(getTrainingSets(), 100000);
    }

    protected abstract MlDataSet getTrainingSets();

    public Output ponder(Input input) {
        double[] data = input.toArray();

        double[] predicted = this.net.predict(data);

        return Output.fromArray(predicted);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

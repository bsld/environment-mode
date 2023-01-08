package com.company.environmentmodel.environment.member.animal.brain;

import com.company.environmentmodel.neuro.Functions;
import com.company.environmentmodel.neuro.MlDataSet;
import com.company.environmentmodel.neuro.NeuralNetwork;

public abstract class Brain implements Cloneable {
    protected NeuralNetwork net;

    protected Brain() {
        net = new NeuralNetwork(Input.getSize(), 13, Action.getSize());
        net.setActivationFunction(Functions.leakyReLu());
        // net.setActivationFunction(Functions.sigmoid());
        net.init();
        net.setLearningRate(0.01);
        // net.setMomentum(0.5);
        net.setMomentum(0.6);
        train();
    }

    void train() {
        this.net.train(getTrainingSets(), 120000);
    }

    protected abstract MlDataSet getTrainingSets();

    public Action ponder(Input input) {
        double[] data = input.toArray();

        double[] predicted = this.net.predict(data);

        return Action.fromArray(predicted);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

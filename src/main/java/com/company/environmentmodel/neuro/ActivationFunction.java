package com.company.environmentmodel.neuro;

public class ActivationFunction {
    public static Function sigmoid() {
        return new Sigmoid();
    }

    public static Function leakyReLu() {
        return new LeakyReLu();
    }

    public static Function swish() {
        return new Swish();
    }

    public static Function tanH() {
        return new TanH();
    }
}

class Sigmoid implements Function {
    @Override
    public double output(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    @Override
    public double outputDerivative(double x) {
        return x * (1 - x);
    }
}

class LeakyReLu implements Function {
    @Override
    public double output(double x) {
        return x >= 0 ? x : x * 0.01;
    }

    @Override
    public double outputDerivative(double x) {
        return x >= 0 ? 1 : 0.01;
    }
}

class Swish implements Function {
    @Override
    public double output(double x) {
        return x * (1 / (1 + Math.exp(-x)));
    }

    @Override
    public double outputDerivative(double x) {
        return ((1 + Math.exp(-x)) + x * Math.exp(-x)) / Math.pow(1 + Math.exp(-x), 2);
    }
}

class TanH implements Function {
    @Override
    public double output(double x) {
        return Math.tanh(x);
    }

    @Override
    public double outputDerivative(double x) {
        return 1 - Math.pow(Math.tanh(x), 2);
    }
}

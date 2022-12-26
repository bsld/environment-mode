package com.company.environmentmodel.neuro;

public final class Functions {
    public static ActivationFunction sigmoid() {
        return new Sigmoid();
    }

    public static ActivationFunction leakyReLu() {
        return new LeakyReLu();
    }

    public static ActivationFunction swish() {
        return new Swish();
    }

    public static ActivationFunction tanH() {
        return new TanH();
    }
}

final class Sigmoid implements ActivationFunction {
    @Override
    public double output(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    @Override
    public double outputDerivative(double x) {
        return x * (1 - x);
    }
}

final class LeakyReLu implements ActivationFunction {
    @Override
    public double output(double x) {
        return x >= 0 ? x : x * 0.01;
    }

    @Override
    public double outputDerivative(double x) {
        return x >= 0 ? 1 : 0.01;
    }
}

final class Swish implements ActivationFunction {
    @Override
    public double output(double x) {
        return x * (1 / (1 + Math.exp(-x)));
    }

    @Override
    public double outputDerivative(double x) {
        return ((1 + Math.exp(-x)) + x * Math.exp(-x)) / Math.pow(1 + Math.exp(-x), 2);
    }
}

final class TanH implements ActivationFunction {
    @Override
    public double output(double x) {
        return Math.tanh(x);
    }

    @Override
    public double outputDerivative(double x) {
        return 1 - Math.pow(Math.tanh(x), 2);
    }
}

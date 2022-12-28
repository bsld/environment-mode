package com.company.environmentmodel.neuro;

public interface ActivationFunction {
    double output(double x);
    double derivative(double x);
}

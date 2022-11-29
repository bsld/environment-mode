package com.company.environmentmodel.neuro;

public class RandomGenerator {
    public static double random(double min, double max) {
        return min + (max - min) * Math.random();
    }

    // public static int random(int min, int max) {
    //     return (int) (min + (max - min) * Math.random());
    // }
}

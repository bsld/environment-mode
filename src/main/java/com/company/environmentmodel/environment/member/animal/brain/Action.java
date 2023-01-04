package com.company.environmentmodel.environment.member.animal.brain;

public enum Action {
    MOVE, EAT, TURN_LEFT, TURN_RIGHT, DIE, REPRODUCE;

    public static int getSize() {
        return 4;
    }

    public static Action fromArray(double[] predicted) {
        for (int i = 0; i < predicted.length; i++) {
            if (Math.abs(1.0 - predicted[i]) <= 0.1) {
                return Action.values()[i];
            }
        }

        // inaccurate prediction
        return Action.MOVE;
    }
    
    public double[] toArray() {
        switch (this) {
            case MOVE:
                return new double[] { 1.0, 0.0, 0.0, 0.0};
            case EAT:
                return new double[] { 0.0, 1.0, 0.0, 0.0};
            case TURN_LEFT:
                return new double[] { 0.0, 0.0, 1.0, 0.0};
            case TURN_RIGHT:
                return new double[] { 0.0, 0.0, 0.0, 1.0};
            default:
                return new double[] { 0.0, 0.0, 0.0, 0.0};
            
        }
    }
}
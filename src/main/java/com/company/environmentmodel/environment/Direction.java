package com.company.environmentmodel.environment;

import java.util.concurrent.ThreadLocalRandom;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;
    
    public static Direction random() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(4)];
    }
}

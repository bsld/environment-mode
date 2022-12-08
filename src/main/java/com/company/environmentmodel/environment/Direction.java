package com.company.environmentmodel.environment;

import java.util.concurrent.ThreadLocalRandom;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public static Direction random() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(4)];
    }

    public Direction rotateClockwise() {
        switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return null;
        }
    }

    public Direction rotateCounterclockwise() {
        switch (this) {
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            default:
                return null;
        }
    }
}

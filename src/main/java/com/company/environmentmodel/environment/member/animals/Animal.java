package com.company.environmentmodel.environment.member.animals;

import com.company.environmentmodel.environment.Direction;
import com.company.environmentmodel.environment.member.Eatable;
import com.company.environmentmodel.environment.member.EnvironmentMember;

public abstract class Animal extends EnvironmentMember implements Eatable {
    protected int energy;
    protected Direction orientation;

    public Animal() {
        this.energy = 100;
        this.orientation = Direction.random();
    }

    public Direction getOrientation() {
        return orientation;
    }
}

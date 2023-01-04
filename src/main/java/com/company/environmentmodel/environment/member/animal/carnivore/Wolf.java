package com.company.environmentmodel.environment.member.animal.carnivore;

import java.util.List;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.Eatable;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.Animal;
import com.company.environmentmodel.environment.member.animal.brain.Action;
import com.company.environmentmodel.environment.member.animal.herbivore.Rabbit;

public class Wolf extends Carnivore {

    public Wolf(Environment environment) {
        super(environment);
        this.name = "Wolf";
    }

    @Override
    public double nutrition() {
        return 31.0;
    }

    @Override
    protected Eatable getFood(List<EnvironmentMember> nearbyCells) {
        for (EnvironmentMember m : nearbyCells) {
            if (m instanceof Rabbit) {
                return (Eatable) m;
            }
        }
        return null;
    }

    @Override
    protected Animal getOffspring() {
        return new Wolf(environment);
    }
}

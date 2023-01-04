package com.company.environmentmodel.environment.member.animal.herbivore;

import java.util.List;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.Eatable;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.Animal;
import com.company.environmentmodel.environment.member.plants.Carrot;

public class Rabbit extends Herbivore {

    public Rabbit(Environment environment) {
        super(environment);
        this.name = "Rabbit";
    }

    @Override
    public double nutrition() {
        return 23.0;
    }

    @Override
    protected Eatable getFood(List<EnvironmentMember> nearbyCells) {
        for (EnvironmentMember m : nearbyCells) {
            if (m instanceof Carrot) {
                return (Eatable) m;
            }
        }
        return null;
    }

    @Override
    protected Animal getOffspring() {
        return new Rabbit(environment);
    }
}

package com.company.environmentmodel.environment.member.animal.carnivore;

import java.util.List;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.Eatable;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.Animal;

public class Cat extends Carnivore {

    public Cat(Environment environment) {
        super(environment);
        this.name = "Cat";
    }

    @Override
    public double nutrition() {
        return 17.0;
    }

    @Override
    protected Eatable getFood(List<EnvironmentMember> nearbyCells) {
        for (EnvironmentMember m : nearbyCells) {
            if (m instanceof Rat) {
                return (Eatable) m;
            }
        }
        return null;
    }

    @Override
    protected Animal getOffspring() {
        return new Cat(environment);
    }

}

package com.company.environmentmodel.environment.member.animal.carnivore;

import java.util.List;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.Eatable;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.Animal;
import com.company.environmentmodel.environment.member.plants.Carrot;

public class Rat extends Carnivore{
    public Rat(Environment environment) {
        super(environment);
        this.name = "Rat";
    }

    @Override
    public double nutrition() {
        return 19.0;
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
        return new Rat(environment);
    }
}

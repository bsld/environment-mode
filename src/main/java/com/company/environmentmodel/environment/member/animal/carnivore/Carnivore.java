package com.company.environmentmodel.environment.member.animal.carnivore;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.animal.Animal;
import com.company.environmentmodel.environment.member.animal.brain.BrainFactory;

public abstract class Carnivore extends Animal {

    public Carnivore(Environment environment) {
        super(environment);
        this.brain = BrainFactory.get().getCarnivoreBrain();
    }

}

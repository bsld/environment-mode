package com.company.environmentmodel.environment.member.animal.herbivore;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.animal.Animal;
import com.company.environmentmodel.environment.member.animal.brain.BrainFactory;

public abstract class Herbivore extends Animal {

    public Herbivore(Environment environment) {
        super(environment);
        this.brain = BrainFactory.get().getHerbivoreBrain();
    }

}

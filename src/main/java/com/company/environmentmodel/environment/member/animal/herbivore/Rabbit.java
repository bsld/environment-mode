package com.company.environmentmodel.environment.member.animal.herbivore;

import com.company.environmentmodel.environment.Environment;

public class Rabbit extends Herbivore {

    public Rabbit(Environment environment) {
        super(environment);
        this.name = "Rabbit";
    }

    @Override
    public int nutrition() {
        return 43;
    }

    @Override
    public void update() {
        super.update();
    }
}

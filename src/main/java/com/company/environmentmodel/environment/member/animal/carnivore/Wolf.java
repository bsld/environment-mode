package com.company.environmentmodel.environment.member.animal.carnivore;

import com.company.environmentmodel.environment.Environment;

public class Wolf extends Carnivore {

    public Wolf(Environment environment) {
        super(environment);
        this.name = "Wolf";
    }

    @Override
    public int nutrition() {
        return 73;
    }
}

package com.company.environmentmodel.environment.member.plants;

import com.company.environmentmodel.environment.Environment;

public class Cactus extends Plant {

    public Cactus(Environment environment) {
        super(environment);
        this.name = "Cactus";
    }

    @Override
    public double nutrition() {
        return 33.0;
    }
}

package com.company.environmentmodel.environment.member.plants;

import com.company.environmentmodel.environment.Environment;

public class Carrot extends Plant {

    public Carrot(Environment environment) {
        super(environment);
        this.name = "Carrot";
    }

    @Override
    public double nutrition() {
        return 29.0;
    }
}

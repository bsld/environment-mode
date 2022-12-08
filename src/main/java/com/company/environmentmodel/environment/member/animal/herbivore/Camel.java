package com.company.environmentmodel.environment.member.animal.herbivore;

import com.company.environmentmodel.environment.Environment;

public class Camel extends Herbivore {

    public Camel(Environment environment) {
        super(environment);
        this.name = "Camel";
    }

    @Override
    public int nutrition() {
        return 83;
    }
}

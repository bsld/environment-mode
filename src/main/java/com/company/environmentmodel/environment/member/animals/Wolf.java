package com.company.environmentmodel.environment.member.animals;

public class Wolf extends Carnivore {

    public Wolf() {
        super();
        this.name = "Wolf";
    }

    @Override
    public int nutrition() {
        return 73;
    }

}

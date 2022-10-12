package com.company.environmentmodel.environment.member.animals;

public class Camel extends Herbivore {

    public Camel() {
        super();
        this.name = "Camel";
    }

    @Override
    public int nutrition() {
        return 83;
    }
}

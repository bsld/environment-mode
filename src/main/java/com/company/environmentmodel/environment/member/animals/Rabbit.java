package com.company.environmentmodel.environment.member.animals;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super();
        this.name = "Rabbit";
    }

    @Override
    public int nutrition() {
        return 43;
    }
}

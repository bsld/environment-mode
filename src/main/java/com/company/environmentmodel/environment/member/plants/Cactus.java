package com.company.environmentmodel.environment.member.plants;

import com.company.environmentmodel.environment.member.Eatable;

public class Cactus extends Plant implements Eatable {

    public Cactus() {
        super();
        this.name = "Cactus";
    }

    @Override
    public int nutrition() {
        return 11;
    }
}

package com.company.environmentmodel.environment.member.plants;

import com.company.environmentmodel.environment.member.Eatable;

public class Carrot extends Plant implements Eatable {

    public Carrot() {
        super();
        this.name = "Carrot";
    }

    @Override
    public int nutrition() {
        return 11;
    }
}

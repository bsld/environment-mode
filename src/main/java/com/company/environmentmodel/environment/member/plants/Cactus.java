package com.company.environmentmodel.environment.member.plants;

import com.company.environmentmodel.environment.member.Eatable;


public class Cactus extends Plant implements Eatable {

   
    
    @Override
    public String getName() {
        return "Cactus";
    }

    @Override
    public int nutrition() {
        return 11;
    }
}

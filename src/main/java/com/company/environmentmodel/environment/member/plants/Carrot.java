package com.company.environmentmodel.environment.member.plants;

import com.company.environmentmodel.environment.member.Eatable;


public class Carrot extends Plant implements Eatable {

   
    
    @Override
    public String getName() {
        return "Carrot";
    }

    @Override
    public int nutrition() {
        return 11; //To change body of generated methods, choose Tools | Templates.
    }
}

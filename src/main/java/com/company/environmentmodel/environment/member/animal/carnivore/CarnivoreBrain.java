package com.company.environmentmodel.environment.member.animal.carnivore;

import com.company.environmentmodel.environment.member.animal.brain.Brain;
import com.company.environmentmodel.neuro.MlDataSet;

public class CarnivoreBrain extends Brain {

    @Override
    protected MlDataSet getTrainingSets() {
        System.out.println("carnivore sets");
        return null;
    }
    
}

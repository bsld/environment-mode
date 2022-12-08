package com.company.environmentmodel.environment.member.animal.herbivore;

import com.company.environmentmodel.environment.member.animal.brain.Brain;
import com.company.environmentmodel.neuro.MlDataSet;

public class HerbivoreBrain extends Brain {

    @Override
    protected MlDataSet getTrainingSets() {
        System.out.println("herbivore sets");
        return null;
    }
}

package com.company.environmentmodel.environment.member.animal.herbivore;

import java.util.ArrayList;
import java.util.List;

import com.company.environmentmodel.environment.member.animal.brain.Brain;
import com.company.environmentmodel.environment.member.animal.brain.Input;
import com.company.environmentmodel.environment.member.animal.brain.Output;
import com.company.environmentmodel.neuro.MlDataSet;

public class HerbivoreBrain extends Brain {

    @Override
    protected MlDataSet getTrainingSets() {
        List<Input> inputs = new ArrayList<>();
        List<Output> targets = new ArrayList<>();
        
        return new MlDataSet(inputs, targets);
    }
}

package com.company.environmentmodel.environment.member.animal.brain;

import com.company.environmentmodel.environment.member.animal.carnivore.CarnivoreBrain;
import com.company.environmentmodel.environment.member.animal.herbivore.HerbivoreBrain;

public class BrainFactory {

    private static BrainFactory instance;
    private static CarnivoreBrain carnivoreBrain;
    private static HerbivoreBrain herbivoreBrain;

    private BrainFactory() {

    }

    public static BrainFactory get() {
        if (instance == null) {
            instance = new BrainFactory();
            carnivoreBrain = new CarnivoreBrain();
            herbivoreBrain = new HerbivoreBrain();
        }

        return instance;
    }

    public CarnivoreBrain getCarnivoreBrain() {
        try {
            return (CarnivoreBrain) carnivoreBrain.clone();
        } catch (Exception e) {
            return null;
        }
    }

    public HerbivoreBrain getHerbivoreBrain() {
        try {
            return (HerbivoreBrain) herbivoreBrain.clone();
        } catch (Exception e) {
            return null;
        }
    }
}

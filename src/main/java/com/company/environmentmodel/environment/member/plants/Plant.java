package com.company.environmentmodel.environment.member.plants;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.Eatable;
import com.company.environmentmodel.environment.member.EnvironmentMember;

public abstract class Plant extends EnvironmentMember implements Eatable {
    private boolean recovering;
    
    public Plant(Environment environment) {
        super(environment);
        this.recovering = false;
    }

    @Override
    public void getEaten() {
        this.timer = 10;
        this.recovering = true;
    }
    
    @Override
    public boolean isEatable() {
        return !isRecovering();
    }

    @Override
    protected void timerAction() {
        this.recovering = false;
    }

    public boolean isRecovering() {
        return recovering;
    }
}

package com.company.environmentmodel.environment.member;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.Tuple;

public abstract class EnvironmentMember {
    protected Tuple position;
    protected String name;
    protected Environment environment;

    /**
     * A timer for something.
     * <p>
     * `-1` means that timer is inactive.
     */
    protected int timer = -1;

    public EnvironmentMember(Environment environment) {
        this.environment = environment;
    }

    public void update() {
        if (this.timer > 0) {
            this.timer--;

            if (this.timer == 0) {
                this.timer = -1;
                this.timerAction();
            }
        }
    }

    protected abstract void timerAction();

    public final Tuple getPosition() {
        return position;
    }

    public final void setPosition(Tuple position) {
        this.position = position;
    }

    public final String getName() {
        return name;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public int getTimer() {
        return timer;
    }
}

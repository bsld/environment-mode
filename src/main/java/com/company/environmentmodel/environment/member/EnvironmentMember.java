package com.company.environmentmodel.environment.member;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.Tuple;

public abstract class EnvironmentMember {
    protected Tuple position;
    protected String name;
    protected Environment environment;

    public EnvironmentMember(Environment environment) {
        this.environment = environment;
    }

    /**
     * The number of frames after which this member will be deleted from the
     * environment.
     * <p>
     * `-1` indicates unlimited amount of frames.
     */
    protected int timeToLive = -1;

    public void update() {
        if (this.timeToLive > 0) {
            this.timeToLive--;
        }
    }

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

    public int getTimeToLive() {
        return timeToLive;
    }
}

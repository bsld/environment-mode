package com.company.environmentmodel.environment.member;

import com.company.environmentmodel.environment.Tuple;

public abstract class EnvironmentMember {
    protected Tuple position;
    protected String name;
    
    /**
     * The number of frames after which this member will be deleted from the environment.
     * <p>
     * `-1` indicates unlimited amount of frames.
     */
    protected int timeToLive = -1;
    
    public void update() {
        if (this.timeToLive > 0) {
            this.timeToLive--;
        }
        // System.out.printf("ttl: %d\n", this.timeToLive);
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
    
    public int getTimeToLive() {
        return timeToLive;
    }
}

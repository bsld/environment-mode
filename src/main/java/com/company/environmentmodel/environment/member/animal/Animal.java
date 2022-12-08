package com.company.environmentmodel.environment.member.animal;

import com.company.environmentmodel.environment.Direction;
import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.Tuple;
import com.company.environmentmodel.environment.member.Eatable;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.brain.Brain;
import com.company.environmentmodel.environment.member.animal.brain.Input;

public abstract class Animal extends EnvironmentMember implements Eatable {
    protected Brain brain;
    protected int energy;
    protected Direction orientation;
    protected Input recent;

    public Animal(Environment environment) {
        super(environment);
        this.energy = 100;
        this.orientation = Direction.random();
        // this.brain = new Brain();
    }

    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
    }

    public Direction getOrientation() {
        return orientation;
    }

    @Override
    public void update() {
        super.update();
        
        this.collectSensoryData();
        
        switch (this.brain.ponder(this.recent)) {
            case MOVE:
                this.move();;
                break;
            case EAT:
                this.eat();
                break;
            case TURN_LEFT:
                this.turnLeft();
                break;
            case TURN_RIGHT:
                this.turnRight();
                break;
            default:
                break;
        }
    }

    public void collectSensoryData() {
        this.recent = new Input(this);
    }

    public void move() {
        Tuple t;
        switch (orientation) {
            case NORTH:
                t = new Tuple(0, -1);
                break;
            case EAST:
                t = new Tuple(1, 0);
                break;
            case SOUTH:
                t = new Tuple(0, 1);
                break;
            case WEST:
                t = new Tuple(-1, 0);
                break;
            default:
                t = new Tuple(0, 0);
                break;
        }

        position = position.addMod(t, environment.getWidth(), environment.getHeight());
    }
    
    public void eat() {
        
    }
    
    public void turnLeft() {
        orientation = orientation.rotateCounterclockwise();
    }
    
    public void turnRight() {
        orientation = orientation.rotateClockwise();
    }
}

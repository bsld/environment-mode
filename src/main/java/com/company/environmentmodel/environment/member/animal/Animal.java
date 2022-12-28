package com.company.environmentmodel.environment.member.animal;

import java.util.ArrayDeque;
import java.util.Queue;

import com.company.environmentmodel.environment.Direction;
import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.Tuple;
import com.company.environmentmodel.environment.member.Eatable;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.brain.Brain;
import com.company.environmentmodel.environment.member.animal.brain.Input;
import com.company.environmentmodel.environment.member.animal.brain.Action;

public abstract class Animal extends EnvironmentMember implements Eatable {
    protected Brain brain;
    protected int energy;
    protected Direction orientation;
    protected Input recent;
    protected Queue<Action> actionQueue;

    public Animal(Environment environment) {
        super(environment);
        this.energy = 100;
        this.orientation = Direction.random();
        this.actionQueue = new ArrayDeque<>();
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

        if (actionQueue.peek() != null) {
            doAction(actionQueue.poll());
            return;
        }

        this.collectSensoryData();

        doAction(this.brain.ponder(this.recent));
    }

    private void doAction(Action action) {
        System.out.println(action);

        switch (action) {
            case MOVE:
                this.move();
                break;
            case EAT:
                this.eat();
                break;
            case TURN_LEFT:
                this.turnLeft();
                this.actionQueue.offer(Action.MOVE);
                break;
            case TURN_RIGHT:
                this.turnRight();
                this.actionQueue.offer(Action.MOVE);
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

        Tuple newPosition = position.addMod(t, environment.getWidth(), environment.getHeight());
        
        if (!this.environment.isOccupied(newPosition)) {
            this.position = newPosition;
        } else {
            System.out.println("occupied");
            this.actionQueue.add(Action.TURN_LEFT);
        }
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

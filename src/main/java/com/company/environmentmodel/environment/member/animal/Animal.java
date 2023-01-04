package com.company.environmentmodel.environment.member.animal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.company.environmentmodel.environment.Direction;
import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.Tuple;
import com.company.environmentmodel.environment.member.Eatable;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.brain.Brain;
import com.company.environmentmodel.environment.member.animal.brain.Input;
import com.company.environmentmodel.environment.member.animal.brain.Action;

public abstract class Animal extends EnvironmentMember implements Eatable {
    protected boolean alive;

    protected Brain brain;
    protected double energy;
    protected Direction orientation;
    protected Input recent;
    protected ArrayDeque<Action> actionQueue;

    public Animal(Environment environment) {
        super(environment);
        this.energy = 77.0;
        this.alive = true;
        this.orientation = Direction.random();
        this.actionQueue = new ArrayDeque<>();
    }

    public boolean isAlive() {
        return alive;
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

        if (energy >= 90.0) {
            actionQueue.offer(Action.REPRODUCE);
        } else if (energy < 0) {
            actionQueue.addFirst(Action.DIE);
        }
        
        if (alive) {
            if (actionQueue.peek() != null) {
                doAction(actionQueue.poll());
                return;
            }

            collectSensoryData();

            doAction(brain.ponder(recent));
        }
    }

    private void doAction(Action action) {
        switch (action) {
            case MOVE:
                this.move();
                this.energy -= 0.7;
                break;
            case EAT:
                this.eat();
                break;
            case TURN_LEFT:
                this.turnLeft();
                this.energy -= 0.7;
                this.actionQueue.offer(Action.MOVE);
                break;
            case TURN_RIGHT:
                this.turnRight();
                this.energy -= 0.7;
                this.actionQueue.offer(Action.MOVE);
                break;
            case DIE:
                this.alive = false;
                this.timer = 10;
                break;
            case REPRODUCE:
                this.reproduce();
                this.energy -= 43.23;
                break;
            default:
                break;
        }
    }

    private void reproduce() {
        Animal child = this.getOffspring();
        child.energy = 37.0;
        
        if (!this.environment.addMemberNearby(this.position, child)) {
            // unable to reproduce :(
        }
    }

    protected abstract Animal getOffspring();

    public void collectSensoryData() {
        this.recent = new Input(this);
    }

    public boolean move() {
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
            return true;
        } else {
            this.actionQueue.add(Action.TURN_LEFT);
            return false;
        }
    }

    public boolean eat() {
        Eatable food = getFood(getNearbyCells());

        if (food != null && food.isEatable()) {
            this.energy += food.nutrition();
            food.getEaten();
            return true;
        }

        this.actionQueue.offer(Action.MOVE);
        return false;
    }

    protected abstract Eatable getFood(List<EnvironmentMember> nearbyCells);

    @Override
    public void getEaten() {
        actionQueue.addFirst(Action.DIE);
    }

    @Override
    public boolean isEatable() {
        return isAlive();
    }

    @Override
    protected void timerAction() {
        this.environment.getMembers().remove(this);
    }

    private List<EnvironmentMember> getNearbyCells() {
        List<Tuple> nearby;

        switch (this.orientation) {
            case NORTH:
                nearby = Arrays.asList(new Tuple(-1, 0), new Tuple(-1, -1), new Tuple(0, -1),
                        new Tuple(1, -1),
                        new Tuple(1, 0));
                break;

            case EAST:
                nearby = Arrays.asList(new Tuple(0, -1), new Tuple(1, -1), new Tuple(1, 0),
                        new Tuple(1, 1),
                        new Tuple(0, 1));

                break;

            case SOUTH:
                nearby = Arrays.asList(new Tuple(1, 0), new Tuple(1, 1), new Tuple(0, 1),
                        new Tuple(-1, 1),
                        new Tuple(-1, 0));

                break;

            case WEST:
                nearby = Arrays.asList(new Tuple(0, 1), new Tuple(-1, 1), new Tuple(-1, 0),
                        new Tuple(-1, -1),
                        new Tuple(0, -1));
                break;

            default:
                nearby = new ArrayList<>();
                break;
        }

        int width = environment.getWidth();
        int height = environment.getHeight();

        return nearby.stream().map(t -> {
            Tuple p = this.position.addMod(t, width, height);
            return this.environment.getByPos(p);
        }).toList();
    }

    public void turnLeft() {
        orientation = orientation.rotateCounterclockwise();
    }

    public void turnRight() {
        orientation = orientation.rotateClockwise();
    }
}

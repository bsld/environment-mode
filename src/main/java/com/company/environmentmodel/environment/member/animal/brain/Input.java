package com.company.environmentmodel.environment.member.animal.brain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.company.environmentmodel.environment.Tuple;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.Animal;
import com.company.environmentmodel.environment.member.animal.carnivore.Carnivore;
import com.company.environmentmodel.environment.member.animal.herbivore.Herbivore;
import com.company.environmentmodel.environment.member.plants.Plant;

public final class Input {
    Animal member;

    List<EnvironmentMember> nearby;
    List<EnvironmentMember> left;
    List<EnvironmentMember> front;
    List<EnvironmentMember> right;

    public Input(Animal member) {
        this.member = member;
        initialize();
    }

    public static int getSize() {
        return 12;
    }

    private void initialize() {
        List<Tuple> left;
        List<Tuple> front;
        List<Tuple> nearby;
        List<Tuple> right;

        switch (this.member.getOrientation()) {
            case NORTH:
                nearby = Arrays.asList(new Tuple(-1, 0), new Tuple(-1, -1), new Tuple(0, -1),
                        new Tuple(1, -1),
                        new Tuple(1, 0));
                left = Arrays.asList(new Tuple(-2, 0), new Tuple(-2, -1), new Tuple(-2, -2), new Tuple(-1, -2));
                front = Arrays.asList(new Tuple(0, -2));
                right = Arrays.asList(new Tuple(2, 0), new Tuple(2, -1), new Tuple(2, -2), new Tuple(1, -2));
                break;

            case EAST:
                nearby = Arrays.asList(new Tuple(0, -1), new Tuple(1, -1), new Tuple(1, 0),
                        new Tuple(1, 1),
                        new Tuple(0, 1));
                left = Arrays.asList(new Tuple(0, -2), new Tuple(1, -2), new Tuple(2, -2), new Tuple(2, -1));
                front = Arrays.asList(new Tuple(2, 0));
                right = Arrays.asList(new Tuple(0, 2), new Tuple(1, 2), new Tuple(2, 1), new Tuple(2, 2));

                break;

            case SOUTH:
                nearby = Arrays.asList(new Tuple(1, 0), new Tuple(1, 1), new Tuple(0, 1),
                        new Tuple(-1, 1),
                        new Tuple(-1, 0));
                left = Arrays.asList(new Tuple(2, 0), new Tuple(2, 1), new Tuple(2, 2), new Tuple(1, 2));
                front = Arrays.asList(new Tuple(0, 2));
                right = Arrays.asList(new Tuple(-2, 0), new Tuple(-2, 1), new Tuple(-1, 2), new Tuple(-2, 2));

                break;

            case WEST:
                nearby = Arrays.asList(new Tuple(0, 1), new Tuple(-1, 1), new Tuple(-1, 0),
                        new Tuple(-1, -1),
                        new Tuple(0, -1));
                left = Arrays.asList(new Tuple(0, 2), new Tuple(-1, 2), new Tuple(-2, 2), new Tuple(-2, 1));
                front = Arrays.asList(new Tuple(-2, 0));
                right = Arrays.asList(new Tuple(0, -2), new Tuple(-1, -2), new Tuple(-2, -1), new Tuple(-2, -2));
                break;

            default:
                nearby = new ArrayList<>();
                left = new ArrayList<>();
                front = new ArrayList<>();
                right = new ArrayList<>();
                break;
        }

        int width = this.member.getEnvironment().getWidth();
        int height = this.member.getEnvironment().getHeight();

        this.nearby = nearby.stream().map(t -> {
            Tuple p = member.getPosition().addMod(t, width, height);
            return this.member.getEnvironment().getByPos(p);
        }).toList();

        this.left = left.stream().map(t -> {
            Tuple p = member.getPosition().addMod(t, width, height);
            return this.member.getEnvironment().getByPos(p);
        }).toList();

        this.front = front.stream().map(t -> {
            Tuple p = member.getPosition().addMod(t, width, height);
            return this.member.getEnvironment().getByPos(p);
        }).toList();

        this.right = right.stream().map(t -> {
            Tuple p = member.getPosition().addMod(t, width, height);
            return this.member.getEnvironment().getByPos(p);
        }).toList();
    }

    public double[] toArray() {
        double[] data = new double[getSize()];

        for (EnvironmentMember m : nearby) {
            if (m instanceof Plant) {
                data[0] = 1.0;
            } else if (m instanceof Herbivore) {
                data[1] = 1.0;
            } else if (m instanceof Carnivore) {
                data[2] = 1.0;
            }
        }

        for (EnvironmentMember m : left) {
            if (m instanceof Plant) {
                data[3] = 1.0;
            } else if (m instanceof Herbivore) {
                data[4] = 1.0;
            } else if (m instanceof Carnivore) {
                data[5] = 1.0;
            }
        }

        for (EnvironmentMember m : front) {
            if (m instanceof Plant) {
                data[6] = 1.0;
            } else if (m instanceof Herbivore) {
                data[7] = 1.0;
            } else if (m instanceof Carnivore) {
                data[8] = 1.0;
            }
        }

        for (EnvironmentMember m : right) {
            if (m instanceof Plant) {
                data[9] = 1.0;
            } else if (m instanceof Herbivore) {
                data[10] = 1.0;
            } else if (m instanceof Carnivore) {
                data[11] = 1.0;
            }
        }

        return data;
    }

    public Input(List<EnvironmentMember> nearby, List<EnvironmentMember> left, List<EnvironmentMember> front,
            List<EnvironmentMember> right) {
        this.nearby = nearby;
        this.left = left;
        this.front = front;
        this.right = right;
    }
}
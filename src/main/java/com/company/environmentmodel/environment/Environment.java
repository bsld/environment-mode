package com.company.environmentmodel.environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.brain.BrainFactory;
import com.company.environmentmodel.environment.member.animal.carnivore.Wolf;
import com.company.environmentmodel.environment.member.animal.herbivore.Camel;
import com.company.environmentmodel.environment.member.animal.herbivore.Rabbit;
import com.company.environmentmodel.environment.member.plants.Cactus;
import com.company.environmentmodel.environment.member.plants.Carrot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environment {
    private int width, height;
    // private int occupiedCells;
    private int totalCells;
    private ObservableList<EnvironmentMember> members;

    public Environment(int width, int height) {
        this.setSize(width, height);
        this.members = FXCollections.observableArrayList();
        BrainFactory.get();
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.totalCells = width * height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOccupiedCells() {
        return members.size();
    }

    public int getTotalCells() {
        return totalCells;
    }

    public void update() {
        // we will call this method in a loop/thread to update our environment
        // (to move members and stuff)
        for (EnvironmentMember m : this.members) {
            m.update();
            // if (m instanceof Rabbit) {
            // m.update();
            // }
        }
    }

    public ObservableList<EnvironmentMember> getMembers() {
        return members;
    }

    public void reset() {
        this.members.clear();
    }

    public boolean addMember(EnvironmentMember member) {
        if (this.getOccupiedCells() == this.totalCells) {
            return false;
        }

        int x;
        int y;

        while (true) {
            x = ThreadLocalRandom.current().nextInt(0, width);
            y = ThreadLocalRandom.current().nextInt(0, height);
            Tuple pos = new Tuple(x, y);

            System.out.println(pos.toString());

            if (getByPos(pos) == null) {
                member.setPosition(pos);
                members.add(member);
                break;
            }
        }

        return true;
    }

    public boolean addRandomMember() {
        float c = ThreadLocalRandom.current().nextFloat();

        EnvironmentMember m = null;
        if (c <= 0.25f) {
            m = new Carrot(this);
        } else if (c <= 0.50f) {
            m = new Rabbit(this);
        } else if (c <= 0.65f) {
            m = new Wolf(this);
        } else if (c <= 0.80f) {
            m = new Cactus(this);
        } else if (c <= 1) {
            m = new Camel(this);
        }

        return this.addMember(m);
    }

    public void addTestLayout(Tuple pos, Direction dir) {
        Rabbit r = new Rabbit(this);
        r.setPosition(pos);
        r.setOrientation(dir);

        this.members.add(r);

        List<Tuple> left;
        List<Tuple> front;
        List<Tuple> nearby;
        List<Tuple> right;

        switch (r.getOrientation()) {
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

        front.stream().map(t -> r.getPosition().addMod(t, this.width, this.height)).forEach(p -> {
            Carrot m = new Carrot(this);
            m.setPosition(p);
            this.members.add(m);
        });

        nearby.stream().map(t -> r.getPosition().addMod(t, this.width, this.height)).forEach(p -> {
            Cactus m = new Cactus(this);
            m.setPosition(p);
            this.members.add(m);
        });

        left.stream().map(t -> r.getPosition().addMod(t, this.width, this.height)).forEach(p -> {
            Camel m = new Camel(this);
            m.setPosition(p);
            this.members.add(m);
        });

        right.stream().map(t -> r.getPosition().addMod(t, this.width, this.height)).forEach(p -> {
            Wolf m = new Wolf(this);
            m.setPosition(p);
            this.members.add(m);
        });
    }

    public boolean addRandomMembers(int n) {
        for (int i = 0; i < n; i++) {
            if (!this.addRandomMember()) {
                return false;
            }
        }

        return true;
    }

    public EnvironmentMember getByPos(int x, int y) {
        return getByPos(new Tuple(x, y));
    }

    public EnvironmentMember getByPos(Tuple pos) {
        for (EnvironmentMember m : this.members) {
            if (m.getPosition().equals(pos)) {
                return m;
            }
        }

        return null;
    }
}
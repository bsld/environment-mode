package com.company.environmentmodel.environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.Animal;
import com.company.environmentmodel.environment.member.animal.brain.BrainFactory;
import com.company.environmentmodel.environment.member.animal.carnivore.*;
import com.company.environmentmodel.environment.member.animal.herbivore.*;
import com.company.environmentmodel.environment.member.plants.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environment {
    private int width, height;
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
        try {
            for (EnvironmentMember m : this.members) {
                m.update();
            }
        } catch (ConcurrentModificationException e) {

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
        } else if (c <= 0.38f) {
            m = new Rabbit(this);
        } else if (c <= 0.45f) {
            m = new Wolf(this);
        } else if (c <= 0.55f) {
            m = new Camel(this);
        } else if (c <= 0.65) {
            m = new Cat(this);
        } else if (c <= 0.81) {
            m = new Rat(this);
        } else if (c <= 1) {
            m = new Cactus(this);
        }

        return this.addMember(m);
    }

    public void addTestLayout2(Tuple pos, Direction dir) {
        Wolf w = new Wolf(this);
        w.setPosition(pos);
        w.setOrientation(dir);

        this.members.add(w);

        // Rabbit r = new Rabbit(this);
        // r.setPosition(pos.addMod(new Tuple(-2, 0), totalCells, height));
        // r.setOrientation(dir);

        // this.members.add(r);

        // r = new Rabbit(this);
        // r.setPosition(pos.addMod(new Tuple(0, -2), totalCells, height));
        // r.setOrientation(dir);

        // this.members.add(r);

        // r = new Rabbit(this);
        // r.setPosition(pos.addMod(new Tuple(2, 0), totalCells, height));
        // r.setOrientation(dir);

        // this.members.add(r);
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

    public boolean isOccupied(int x, int y) {
        return isOccupied(new Tuple(x, y));
    }

    public boolean isOccupied(Tuple newPosition) {
        return getByPos(newPosition) != null;
    }

    public boolean addMemberNearby(Tuple pos, Animal child) {
        for (int i = -3; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                Tuple newPos = pos.addMod(new Tuple(i, j), width, height);
                if (!isOccupied(newPos)) {
                    child.setPosition(newPos);
                    this.members.add(child);
                    return true;
                }
            }
        }

        return false;
    }
}
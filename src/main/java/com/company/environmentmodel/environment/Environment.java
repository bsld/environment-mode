package com.company.environmentmodel.environment;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animals.Camel;
import com.company.environmentmodel.environment.member.animals.Rabbit;
import com.company.environmentmodel.environment.member.animals.Wolf;
import com.company.environmentmodel.environment.member.plants.Cactus;
import com.company.environmentmodel.environment.member.plants.Carrot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environment {
    private int width, height;
    // private int occupiedCells;
    private int totalCells;
    private ArrayList<Tuple> unoccupiedCells;
    private ObservableList<EnvironmentMember> members;

    public Environment(int width, int height) {
        this.setSize(width, height);
        this.members = FXCollections.observableArrayList();
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.totalCells = width * height;

        this.unoccupiedCells = new ArrayList<>(this.totalCells);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.unoccupiedCells.add(new Tuple(i, j));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOccupiedCells() {
        return totalCells - unoccupiedCells.size();
    }

    public int getTotalCells() {
        return totalCells;
    }
    
    public void update() {
        // we will call this method in a loop/thread to update our environment (to move members and stuff)
        for (EnvironmentMember m : this.members) {
            m.update();
        }
    }
    
    public ObservableList<EnvironmentMember> getMembers() {
        return members;
    }
    
    public void reset() {
        this.unoccupiedCells = new ArrayList<>(this.totalCells);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.unoccupiedCells.add(new Tuple(i, j));
            }
        }

        this.members.clear();
        
        // this.members.remove(12);
        // this.members.remove(10, 19);
    }

    public boolean addMember(EnvironmentMember member) {
        if (this.getOccupiedCells() == this.totalCells) {
            return false;
        }

        int n = ThreadLocalRandom.current().nextInt(0, unoccupiedCells.size());

        member.setPosition(unoccupiedCells.get(n));
        members.add(member);
        unoccupiedCells.remove(n);

        return true;
    }

    public boolean addRandomMember() {
        float c = ThreadLocalRandom.current().nextFloat();

        EnvironmentMember m = null;
        if (c <= 0.25f) {
            m = new Carrot();
        } else if (c <= 0.50f) {
            m = new Rabbit();
        } else if (c <= 0.65f) {
            m = new Wolf();
        } else if (c <= 0.80f) {
            m = new Cactus();
        } else if (c <= 1) {
            m = new Camel();
        }
        ;

        return this.addMember(m);
    }

    public boolean addRandomMembers(int n) {
        for (int i = 0; i < n; i++) {
            if (!this.addRandomMember()) {
                return false;
            }
        }

        return true;
    }
}
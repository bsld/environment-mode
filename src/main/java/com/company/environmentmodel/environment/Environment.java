package com.company.environmentmodel.environment;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animals.Camel;
import com.company.environmentmodel.environment.member.animals.Rabbit;
import com.company.environmentmodel.environment.member.animals.Wolf;
import com.company.environmentmodel.environment.member.plants.Cactus;
import com.company.environmentmodel.environment.member.plants.Carrot;

public class Environment {
    private int width, height;
    // private int occupiedCells;
    private int totalCells;
    private ArrayList<Tuple> unoccupiedCells;
    // private ArrayList<EnvironmentMember> members;

    private Cell[][] cells;

    public Environment(int width, int height) {
        this.setSize(width, height);
        // this.members = new ArrayList<>(memberCount);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.totalCells = width * height;
        this.cells = new Cell[width][height];

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

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public void reset() {
        this.unoccupiedCells = new ArrayList<>(this.totalCells);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.unoccupiedCells.add(new Tuple(i, j));
            }
        }
        
        this.cells = new Cell[width][height];
    }

    public boolean addMember(EnvironmentMember member) {
        if (this.getOccupiedCells() == this.totalCells) {
            return false;
        }

        while (true) {
            int n = ThreadLocalRandom.current().nextInt(0, unoccupiedCells.size());
            Tuple t = unoccupiedCells.get(n);

            if (this.cells[t.x][t.y] == null) {
                this.cells[t.x][t.y] = new Cell(t.x, t.y, member);
                unoccupiedCells.remove(n);
                break;
            }
        }

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

    public boolean move(int i, int j, Direction dir) {
        return false;
    }

    public void print() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (this.cells[i][j] == null) {
                    System.out.print('_');
                } else {
                    char c = this.cells[i][j].getMember().getName().charAt(0);
                    System.out.print(c);
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}

class Tuple {
    public int x, y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
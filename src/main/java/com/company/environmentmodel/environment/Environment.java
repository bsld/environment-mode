package com.company.environmentmodel.environment;

import java.util.concurrent.ThreadLocalRandom;

import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animals.Camel;
import com.company.environmentmodel.environment.member.animals.Rabbit;
import com.company.environmentmodel.environment.member.animals.Wolf;
import com.company.environmentmodel.environment.member.plants.Cactus;
import com.company.environmentmodel.environment.member.plants.Carrot;

public class Environment {
    private int width, height;
    private int occupiedPoints;
    private int totalPoints;
    // private ArrayList<EnvironmentMember> members;

    private Cell[][] cells;

    public Environment(int width, int height) {
        this.setSize(width, height);
        // this.members = new ArrayList<>(memberCount);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.occupiedPoints = 0;
        this.totalPoints = width * height;
        this.cells = new Cell[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOccupiedPoints() {
        return occupiedPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public void reset() {
        this.occupiedPoints = 0;
        this.cells = new Cell[width][height];
    }

    public boolean addMember(EnvironmentMember member) {
        if (this.occupiedPoints == this.totalPoints) {
            return false;
        }

        boolean placed = false;

        int randomX, randomY;
        while (true) {
            randomX = ThreadLocalRandom.current().nextInt(0, width);
            randomY = ThreadLocalRandom.current().nextInt(0, height);

            if (this.cells[randomX][randomY] == null) {
                this.cells[randomX][randomY] = new Cell(randomX, randomY, member);
                placed = true;
                this.occupiedPoints++;

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

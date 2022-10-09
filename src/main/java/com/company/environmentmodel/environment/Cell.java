package com.company.environmentmodel.environment;

import com.company.environmentmodel.environment.member.EnvironmentMember;

public class Cell {
    private EnvironmentMember member;

    private int x, y;

    public Cell(int x, int y, EnvironmentMember member) {
        this.x = x;
        this.y = y;
        this.member = member;
    }

    public EnvironmentMember getMember() {
        return member;
    }

    public void setMember(EnvironmentMember member) {
        this.member = member;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied() {
        return this.member == null;
    }
}

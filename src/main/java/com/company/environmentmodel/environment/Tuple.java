package com.company.environmentmodel.environment;

public final class Tuple {
    public int x, y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Tuple other = (Tuple) obj;

        if (x != other.x)
            return false;

        if (y != other.y)
            return false;

        return true;
    }

    public Tuple add(Tuple rhs) {
        return new Tuple(this.x + rhs.x, this.y + rhs.y);
    }

    public Tuple addMod(Tuple rhs, int xMod, int yMod) {
        return new Tuple(Math.floorMod(this.x + rhs.x, xMod), Math.floorMod(this.y + rhs.y, yMod));
    }

    @Override
    public String toString() {
        return "Tuple [x=" + x + ", y=" + y + "]";
    }
}
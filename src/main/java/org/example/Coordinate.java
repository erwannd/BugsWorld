package org.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class to store points represented on a 2d-plane
 */
public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Create a new object by copying
     * existing object
     */
    public Coordinate(Coordinate point) {
        this.row = point.row;
        this.col = point.col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    @Override
    public String toString() {
        return "{row=" + row + ", col=" + col + '}';
    }

    /**
     * Checks for Coordinate equality.
     * Two Coordinates are equal if they have
     * the same row and col values.
     * @return true if they have the same row and col.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate other = (Coordinate) o;
        return row == other.row && col == other.col;
    }

    /**
     * Hashcode must be overwritten to check for
     * proper equality.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public static void main(String[] args) {
        Set<Coordinate> coordSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            coordSet.add(new Coordinate(i, i*10+10));
        }

        System.out.println(coordSet);
        for (Coordinate point : coordSet) {
            System.out.print(point.getRow());
            System.out.print(", " + point.getCol());
            System.out.println();
        }

        Coordinate coord = new Coordinate(0,20);
        //Override hashCode() method to check for equality below
        System.out.println(coordSet.contains(coord));
    }
}

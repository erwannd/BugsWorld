package org.example;

import java.util.ArrayList;

public abstract class Organism {
    private Coordinate position;
    private int timeStep;
    public boolean hasMoved = false;
    abstract public void move();

    public Organism(Coordinate pos) {
        this.position = pos;
    }

    public Coordinate getPosition() {
        return this.position;
    }

    public void setPosition(Coordinate pos) {
        this.position = pos;
    }

    /** Observe neighboring slots that is unoccupied within the game board.
     * @return ArrayList containing valid Coordinates.
     */
    public ArrayList<Coordinate> getMoves() {
        ArrayList<Coordinate> neighbors = new ArrayList<>();
        int currentRow = this.position.getRow();
        int currentCol = this.position.getCol();
        Coordinate newPos = new Coordinate(currentRow + 1, currentCol);
        if (BugsWorld.isValid(newPos) && BugsWorld.isEmpty(newPos)) {
            neighbors.add(newPos);
        }

        newPos = new Coordinate(currentRow - 1, currentCol);
        if (BugsWorld.isValid(newPos) && BugsWorld.isEmpty(newPos)) {
            neighbors.add(newPos);
        }

        newPos = new Coordinate(currentRow, currentCol + 1);
        if (BugsWorld.isValid(newPos) && BugsWorld.isEmpty(newPos)) {
            neighbors.add(newPos);
        }

        newPos = new Coordinate(currentRow, currentCol - 1);
        if (BugsWorld.isValid(newPos) && BugsWorld.isEmpty(newPos)) {
            neighbors.add(newPos);
        }

        return neighbors;
    }

    public void breed() {
        //code for organism breeding
    }

    public void incrementStep() {
        timeStep++;
    }
}
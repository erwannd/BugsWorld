package org.example;

import java.util.ArrayList;

public abstract class Organism {
    private Coordinate position;
    int timeStep;
    public boolean hasMoved = false;
    abstract public void move();
    abstract public void breed();

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
     * @return ArrayList containing valid Coordinates to move to.
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
}
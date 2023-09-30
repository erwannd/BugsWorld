package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DoodleBug extends Organism {
    public static final int BREED_COEFF = 8;
    private static final int STARVE = 3;
    private int timeSinceLastMeal = 0;
    private int id;
    private static int doodlesCount = 0;
    public static Set<Coordinate> doodlesArr = new HashSet<>();

    public DoodleBug(Coordinate pos) {
        super(pos);
        this.id = ++doodlesCount;
    }

    public DoodleBug(int row, int col) {
        super(new Coordinate(row, col));
        this.id = --doodlesCount;
    }

    public static void initDoodlesArr(int doodlesCount) {
        Random random = new Random();
        for (int i = 0; i < doodlesCount; i++) {
            int row = random.nextInt(BugsWorld.BOARD_SIZE);
            int col = random.nextInt(BugsWorld.BOARD_SIZE);
            Coordinate point = new Coordinate(row, col);
            while(!BugsWorld.isEmpty(point)) {
                row = random.nextInt(BugsWorld.BOARD_SIZE);
                col = random.nextInt(BugsWorld.BOARD_SIZE);
                point = new Coordinate(row, col);
            }
            doodlesArr.add(point);
            DoodleBug doodle = new DoodleBug(point);
            BugsWorld.board[point.getRow()][point.getCol()] = doodle;
        }
    }

    @Override
    public void move() {
        Coordinate antPos = consumeAnts();
        Coordinate oldPos = this.getPosition();
        if (antPos != null) {
            //If found an Ant to consume
            this.setPosition(antPos);
            BugsWorld.board[oldPos.getRow()][oldPos.getCol()] = null;
            BugsWorld.board[antPos.getRow()][antPos.getCol()] = this;
            Ant.antsArr.remove(antPos);
            DoodleBug.doodlesArr.add(antPos);
            timeSinceLastMeal = 0;
        } else {
            //Otherwise moves like an Ant
            ArrayList<Coordinate> validPos = getMoves();
            if (!validPos.isEmpty()) {
                Random rand = new Random();
                int index = rand.nextInt(validPos.size());
                Coordinate newPos = validPos.get(index);
                this.setPosition(newPos);
                doodlesArr.remove(oldPos);
                doodlesArr.add(newPos);
                BugsWorld.board[oldPos.getRow()][oldPos.getCol()] = null;
                BugsWorld.board[newPos.getRow()][newPos.getCol()] = this;
            }
        }
        this.hasMoved = true;
        timeStep++;
        timeSinceLastMeal++;
        starve();
    }

    @Override
    public void breed() {
        if (this.timeStep == BREED_COEFF) {
            this.timeStep = 0;
        } else {
            return;
        }
        ArrayList<Coordinate> validPos = getMoves();
        if (!validPos.isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(validPos.size());
            Coordinate childPos = validPos.get(index);
            DoodleBug child = new DoodleBug(childPos);
            doodlesArr.add(childPos);
            BugsWorld.board[childPos.getRow()][childPos.getCol()] = child;
        }
    }

    private void starve() {
        if (timeSinceLastMeal == STARVE) {
            // Doodlebug has not eaten in 3 timesteps, remove the coordinate from doodlesArr
            Coordinate pos = this.getPosition();
            doodlesArr.remove(pos);
            BugsWorld.board[pos.getRow()][pos.getCol()] = null;
        }
    }

    private Coordinate consumeAnts() {
        ArrayList<Coordinate> prey = findNearbyAnts();
        if (!prey.isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(prey.size());
            return prey.get(index);
        } else {
            return null;
        }
    }

    private ArrayList<Coordinate> findNearbyAnts() {
        ArrayList<Coordinate> nearbyAnts = new ArrayList<>();
        Coordinate currentPos = this.getPosition();

        Coordinate top = new Coordinate(currentPos.getRow() + 1, currentPos.getCol());
        if (BugsWorld.isValid(top) && Ant.antsArr.contains(top)) {
            nearbyAnts.add(top);
        }
        Coordinate bottom = new Coordinate(currentPos.getRow() - 1, currentPos.getCol());
        if (BugsWorld.isValid(bottom) && Ant.antsArr.contains(bottom)) {
            nearbyAnts.add(bottom);
        }
        Coordinate left = new Coordinate(currentPos.getRow(), currentPos.getCol() - 1);
        if (BugsWorld.isValid(left) && Ant.antsArr.contains(left)) {
            nearbyAnts.add(left);
        }
        Coordinate right = new Coordinate(currentPos.getRow(), currentPos.getCol() + 1);
        if (BugsWorld.isValid(right) && Ant.antsArr.contains(right)) {
            nearbyAnts.add(right);
        }
        return nearbyAnts;
    }

    @Override
    public String toString() {
        return "d" + this.id + "t" + timeStep + "m" + timeSinceLastMeal;
    }

    public static void main(String[] args) {
        DoodleBug.initDoodlesArr(BugsWorld.DOODLES_COUNT);
        System.out.println(doodlesArr);
    }
}

package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ant extends Organism {
    public static final int BREED_COEFF = 3;
    private int id;
    private static int antsCount = 0;
    public static Set<Coordinate> antsArr = new HashSet<>();

    public Ant(Coordinate pos) {
        super(pos);
        this.id = ++antsCount;
    }

    public Ant(int row, int col) {
        super(new Coordinate(row, col));
        this.id = ++antsCount;
    }

    public static void initAntsArr(int antsCount) {
        Random random = new Random();
        for (int i = 0; i < antsCount; i++) {
            int row = random.nextInt(BugsWorld.BOARD_SIZE);
            int col = random.nextInt(BugsWorld.BOARD_SIZE);
            Coordinate point = new Coordinate(row, col);
            while(!BugsWorld.isEmpty(point)) {
                row = random.nextInt(BugsWorld.BOARD_SIZE);
                col = random.nextInt(BugsWorld.BOARD_SIZE);
                point = new Coordinate(row, col);
            }
            antsArr.add(point);
            Ant ant = new Ant(point);
            BugsWorld.board[point.getRow()][point.getCol()] = ant;
        }
    }

    @Override
    public void move() {
        ArrayList<Coordinate> validPos = getMoves();
        if (!validPos.isEmpty()) {
            Coordinate oldPos = getPosition();
            Random rand = new Random();
            int index = rand.nextInt(validPos.size());
            Coordinate newPos = validPos.get(index);
            setPosition(newPos);
            antsArr.remove(oldPos);
            antsArr.add(newPos);
            BugsWorld.board[oldPos.getRow()][oldPos.getCol()] = null;
            BugsWorld.board[newPos.getRow()][newPos.getCol()] = this;
        }
        this.hasMoved = true;
        this.timeStep++;
        if (this.timeStep == BREED_COEFF) {
            breed();
            this.timeStep = 0;
        }
    }

    @Override
    public void breed() {
        ArrayList<Coordinate> validPos = getMoves();
        if (!validPos.isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(validPos.size());
            Coordinate childPos = validPos.get(index);
            Ant child = new Ant(childPos);
            antsArr.add(childPos);
            BugsWorld.board[childPos.getRow()][childPos.getCol()] = child;
        }
    }

    @Override
    public String toString() {
        return "a" +  this.id + "t" + timeStep;
    }

    public static void main(String[] args) {
        Ant.initAntsArr(BugsWorld.ANTS_COUNT);
        System.out.println(antsArr);
        Ant ant1 = new Ant(new Coordinate(1,1));
        for (int i = 0; i < 10; i++) {
            System.out.print(ant1.getPosition());
            ant1.move();
            System.out.print(" moveto: " + ant1.getPosition());
            System.out.println();
        }
    }
}
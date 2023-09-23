package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ant extends Organism {
    public static final int BREED_COEFF = 3;
    public static Set<Coordinate> antsArr = new HashSet<>();

    public Ant(Coordinate pos) {
        super(pos);
    }

    public Ant(int row, int col) {
        super(new Coordinate(row, col));
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
        }
    }

    @Override
    public void move() {
        ArrayList<Coordinate> validPos = getMoves();
        Coordinate oldPos = new Coordinate(this.getPosition());
        Random rand = new Random();
        int index = rand.nextInt(validPos.size());
        setPosition(validPos.get(index));
        antsArr.remove(oldPos);
        antsArr.add(new Coordinate(this.getPosition()));
        this.hasMoved = true;
    }

    @Override
    public String toString() {
        return "O";
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
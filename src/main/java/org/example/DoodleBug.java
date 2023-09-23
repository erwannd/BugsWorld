package org.example;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DoodleBug extends Organism {
    public static final int BREED_COEFF = 5;
    public static Set<Coordinate> doodlesArr = new HashSet<>();

    public DoodleBug(Coordinate pos) {
        super(pos);
    }

    public static void initDoodlesArr(int doodlesCount) {
        Random random = new Random();
        for (int i = 0; i < doodlesCount; i++) {
            int row = random.nextInt(BugsWorld.BOARD_SIZE);
            int col = random.nextInt(BugsWorld.BOARD_SIZE);
            Coordinate point = new Coordinate(row, col);
            while(doodlesArr.contains(point) || Ant.antsArr.contains(point)) {
                row = random.nextInt(BugsWorld.BOARD_SIZE);
                col = random.nextInt(BugsWorld.BOARD_SIZE);
                point = new Coordinate(row, col);
            }
            doodlesArr.add(point);
        }
    }

    @Override
    public void move() {

    }

    @Override
    public String toString() {
        return "X";
    }

    public static void main(String[] args) {
        DoodleBug.initDoodlesArr(BugsWorld.DOODLES_COUNT);
        System.out.println(doodlesArr);
    }
}

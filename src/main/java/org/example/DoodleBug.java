package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DoodleBug extends Organism {
    public static final int BREED_COEFF = 5;
    private int id;
    private static int doodlesCount = 100;
    public static Set<Coordinate> doodlesArr = new HashSet<>();

    public DoodleBug(Coordinate pos) {
        super(pos);
        this.id = --doodlesCount;
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
        ArrayList<Coordinate> validPos = getMoves();
        if (!validPos.isEmpty()) {
            Coordinate oldPos = new Coordinate(this.getPosition());
            Random rand = new Random();
            int index = rand.nextInt(validPos.size());
            setPosition(validPos.get(index));
            doodlesArr.remove(oldPos);
            doodlesArr.add(new Coordinate(this.getPosition()));
            BugsWorld.board[oldPos.getRow()][oldPos.getCol()] = null;
            BugsWorld.board[this.getPosition().getRow()][this.getPosition().getCol()] = this;
        }
        this.hasMoved = true;
        timeStep++;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    public static void main(String[] args) {
        DoodleBug.initDoodlesArr(BugsWorld.DOODLES_COUNT);
        System.out.println(doodlesArr);
    }
}

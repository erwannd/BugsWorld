package org.example;

import java.util.Objects;
import java.util.Scanner;

public class BugsWorld {
    public static final int BOARD_SIZE = 5;
    public static int ANTS_COUNT = 5;
    public static int DOODLES_COUNT = 0;
    private static final Organism[][] board = new Organism[BOARD_SIZE][BOARD_SIZE];

    public BugsWorld() {
        Ant.initAntsArr(ANTS_COUNT);
        for (Coordinate a : Ant.antsArr) {
            Ant ant = new Ant(a);
            board[a.getRow()][a.getCol()] = ant;
        }
        DoodleBug.initDoodlesArr(DOODLES_COUNT);
        for (Coordinate d : DoodleBug.doodlesArr) {
            DoodleBug doodle = new DoodleBug(d);
            board[d.getRow()][d.getCol()] = doodle;
        }
    }

    /**
     * Checks if a coordinate is within the game board.
     * @param c Coordinate to be checked.
     * @return true if it is.
     */
    public static boolean isValid(Coordinate c) {
        if (c.getRow() >= 0 && c.getRow() < BugsWorld.BOARD_SIZE) {
            return c.getCol() >= 0 && c.getCol() < BugsWorld.BOARD_SIZE;
        }
        return false;
    }

    public static boolean isEmpty(Coordinate c) {
        return !Ant.antsArr.contains(c) && !DoodleBug.doodlesArr.contains(c);
    }

    @Override
    public String toString() {
        StringBuilder mapRep = new StringBuilder();
        updateWorld();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Object obj = board[row][col];
                if (obj == null) {
                    mapRep.append("_");
                } else {
                    mapRep.append(board[row][col]);
                }
                mapRep.append("\t");
            }
            mapRep.append("\n");
        }
        return mapRep.toString();
    }

    public void updateWorld() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Coordinate c = new Coordinate(row, col);
                if (Ant.antsArr.contains(c)) {
                    board[row][col] = new Ant(c);
                } else {
                    board[row][col] = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        BugsWorld map = new BugsWorld();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ants pos: " + Ant.antsArr);
            System.out.println("Doodles pos: " + DoodleBug.doodlesArr);
            System.out.println(map);
            System.out.print("Continue(Y/N): ");
            String input = scanner.next();
            if (Objects.equals(input, "Y")) {
                for (Organism[] o : board) {
                    for (Organism org : o) {
                        if (org != null && !org.hasMoved) org.move();
                    }
                }
            }
            else if (Objects.equals(input, "N")) break;
        }
    }
}

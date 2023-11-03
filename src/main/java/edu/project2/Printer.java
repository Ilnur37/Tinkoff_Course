package edu.project2;

import edu.project2.cells.Cell;
import edu.project2.cells.Coordinate;
import java.util.List;

public class Printer {
    public static void printMaze(Maze maze) {
        for (Cell[] cells : maze.getGrid()) {
            System.out.println();
            for (Cell cell : cells) {
                switch (cell.getType()) {
                    case PASSAGE -> System.out.print(" ");
                    case WALL -> System.out.print("|");
                    case FLOOR -> System.out.print("_");
                }
            }
        }
    }

    public static void PrintMazeWithPath(Maze maze, List<Coordinate> path) {
        Cell[][] gird = maze.getGrid();
        boolean flag = true;
        for (int i = 0; i < gird.length; i++) {
            System.out.println();
            for (int j = 0; j < gird[0].length; j++) {
                flag = true;
                for (Coordinate coordinate : path) {
                    if (coordinate.row() == i && coordinate.col() == j) {
                        System.out.print("o");
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    switch (gird[i][j].getType()) {
                        case PASSAGE -> System.out.print(" ");
                        case WALL -> System.out.print("|");
                        case FLOOR -> System.out.print("_");
                    }
                }
            }
        }
        return;
    }
}

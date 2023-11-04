package edu.project2;

import edu.project2.cells.Cell;
import edu.project2.cells.Coordinate;
import edu.project2.cells.TypeCell;
import java.util.List;

@SuppressWarnings("RegexpSinglelineJava")
public class Printer {
    private Printer() {

    }

    public static void printMaze(Maze maze) {
        for (Cell[] cells : maze.getGrid()) {
            System.out.println();
            for (Cell cell : cells) {
                printSymbol(cell.getType());
            }
        }
    }

    private static void printSymbol(TypeCell typeCell) {
        switch (typeCell) {
            case WALL -> System.out.print("|");
            case FLOOR -> System.out.print("_");
            default -> System.out.print(" ");
        }
    }

    public static void printMazeWithPath(Maze maze, List<Coordinate> path) {
        for (Cell[] cells : maze.getGrid()) {
            System.out.println();
            for (Cell cell : cells) {
                if (!isPartOfPath(cell, path)) {
                    printSymbol(cell.getType());
                }
            }
        }
    }

    private static boolean isPartOfPath(Cell cell, List<Coordinate> path) {
        int row = cell.getCoordinate().getRow();
        int col = cell.getCoordinate().getCol();
        for (Coordinate cordPath : path) {
            if (cordPath.getRow() == row && cordPath.getCol() == col) {
                System.out.print("o");
                return true;
            }
        }
        return false;
    }
}

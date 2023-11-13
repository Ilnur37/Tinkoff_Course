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
                System.out.print(printSymbol(cell.getType()));
            }
        }
    }

    @SuppressWarnings("InnerAssignment")
    public static char printSymbol(TypeCell typeCell) {
        char symbol;
        switch (typeCell) {
            case WALL -> symbol = '|';
            case FLOOR -> symbol = '_';
            default -> symbol = ' ';
        }
        return symbol;
    }

    public static void printMazeWithPath(Maze maze, List<Coordinate> path) {
        for (Cell[] cells : maze.getGrid()) {
            System.out.println();
            for (Cell cell : cells) {
                if (!isPartOfPath(cell, path)) {
                    System.out.print(printSymbol(cell.getType()));
                }
            }
        }
    }

    private static boolean isPartOfPath(Cell cell, List<Coordinate> path) {
        int row = cell.getCoordinate().row();
        int col = cell.getCoordinate().col();
        for (Coordinate cordPath : path) {
            if (cordPath.row() == row && cordPath.col() == col) {
                System.out.print("o");
                return true;
            }
        }
        return false;
    }
}

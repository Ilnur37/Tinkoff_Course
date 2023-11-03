package edu.project2;

import edu.project2.cells.Cell;
import edu.project2.cells.TypeCell;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public boolean isValidCoordinate(int row, int col) {
        return row < height && col < width && !grid[row][col].isVisited();
    }

    public boolean isPassage(int row, int col) {
        return grid[row][col].getType().equals(TypeCell.PASSAGE);
    }

    public void cellVisited(int row, int col) {
        grid[row][col].cellVisited();
    }
}

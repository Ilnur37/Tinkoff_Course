package edu.project2;

import edu.project2.cells.Cell;

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

    public boolean isInvalidCoordinate(int row, int col) {
        return row >= height || col >= width || grid[row][col].isVisited();
    }
}

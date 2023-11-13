package edu.project2;

import edu.project2.cells.Cell;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Cell[][] getGrid() {
        return grid;
    }

    public boolean isInvalidCoordinate(int row, int col) {
        return row >= height || col >= width;
    }

    public boolean isVisitedCoordinate(int row, int col) {
        return grid[row][col].isVisited();
    }
}

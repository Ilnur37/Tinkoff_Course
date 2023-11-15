package edu.project2.cells;

import lombok.Getter;

@Getter
public class Cell {
    private final Coordinate coordinate;
    private final TypeCell type;
    private boolean isVisited;

    public Cell(int row, int col, TypeCell typeCell) {
        this.coordinate = new Coordinate(row, col);
        this.type = typeCell;
        this.isVisited = false;
    }

    public void setVisited(boolean bool) {
        isVisited = bool;
    }
}

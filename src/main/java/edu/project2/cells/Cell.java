package edu.project2.cells;

import lombok.Getter;

@Getter
public class Cell {
    private final int row;
    private final int col;
    private final TypeCell type;
    private boolean isVisited;

    public Cell(int row, int col, TypeCell typeCell) {
        this.row = row;
        this.col = col;
        this.type = typeCell;
        this.isVisited = false;
    }

    public void cellVisited() {
        isVisited = true;
    }
}

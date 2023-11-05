package edu.project2.solver;

import edu.project2.Maze;
import edu.project2.cells.Cell;
import edu.project2.cells.Coordinate;
import java.util.List;

public abstract class Solver {
    private final int scaleWidth = 4;
    public final Coordinate start;
    public final Coordinate end;
    public final int[][] directions = {
        {0, scaleWidth},
        {1, 0},
        {0, -1 * scaleWidth},
        {-1, 0}};
    public final Coordinate[] cordForCheckBorder = new Coordinate[] {
        new Coordinate(0, 2),
        new Coordinate(0, 0),
        new Coordinate(0, -2),
        new Coordinate(-1, 0)
    };

    protected Solver(Coordinate start, Coordinate end) {
        this.start = new Coordinate(start.row(), start.col() * scaleWidth - 2);
        this.end = new Coordinate(end.row(), end.col() * scaleWidth - 2);
    }

    abstract List<Coordinate> solve(Maze maze);

    boolean isEnd(int row, int col) {
        return row == end.row() && col == end.col();
    }

    void cleanFieldIsVisited(Maze maze) {
        for (Cell[] cells : maze.getGrid()) {
            for (Cell cell : cells) {
                maze.getGrid()[cell.getCoordinate().row()][cell.getCoordinate().col()].setVisited(false);
            }
        }
    }
}

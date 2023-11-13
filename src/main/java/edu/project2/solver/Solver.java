package edu.project2.solver;

import edu.project2.Maze;
import edu.project2.cells.Cell;
import edu.project2.cells.Coordinate;
import java.util.List;

public abstract class Solver {
    private static final int SCALE_WIDTH = 4;
    protected final Coordinate start;
    protected final Coordinate end;
    protected static final int[][] DIRECTIONS = {
        {0, SCALE_WIDTH},
        {1, 0},
        {0, -1 * SCALE_WIDTH},
        {-1, 0}};
    protected static final Coordinate[] CORD_FOR_CHECK_BORDER = new Coordinate[] {
        new Coordinate(0, 2),
        new Coordinate(0, 0),
        new Coordinate(0, -2),
        new Coordinate(-1, 0)
    };

    protected Solver(Coordinate start, Coordinate end) {
        this.start = new Coordinate(start.row(), start.col() * SCALE_WIDTH - 2);
        this.end = new Coordinate(end.row(), end.col() * SCALE_WIDTH - 2);
    }

    public abstract List<Coordinate> solve(Maze maze);

    protected boolean isEnd(int row, int col) {
        return row == end.row() && col == end.col();
    }

    protected void cleanFieldIsVisited(Maze maze) {
        for (Cell[] cells : maze.getGrid()) {
            for (Cell cell : cells) {
                maze.getGrid()[cell.getCoordinate().row()][cell.getCoordinate().col()].setVisited(false);
            }
        }
    }
}

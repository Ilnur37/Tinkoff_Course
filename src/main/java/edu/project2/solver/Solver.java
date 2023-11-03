package edu.project2.solver;

import edu.project2.cells.Coordinate;
import edu.project2.Maze;
import java.util.List;

public abstract class Solver {
    public final int[][] DIRECTIONS
        = { { 0, 4 }, { 1, 0 }, { 0, -4 }, { -1, 0 } };
    public final Coordinate start;
    public final Coordinate end;

    protected Solver(Coordinate start, Coordinate end) {
        this.start = new Coordinate(start.row(), (start.col() - 1) * 3 + start.col() + 1);
        this.end = new Coordinate(end.row(), (end.col() - 1) * 3 + end.col() + 1);
    }

    abstract List<Coordinate> solve(Maze maze);
}

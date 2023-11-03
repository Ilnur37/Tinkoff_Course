package edu.project2.solver;

import edu.project2.cells.Coordinate;
import edu.project2.Maze;
import edu.project2.cells.TypeCell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolverDFSImpl extends Solver {
    public final List<Coordinate> path = new ArrayList<>();

    public SolverDFSImpl(Coordinate start, Coordinate end) {
        super(start, end);
    }

    public List<Coordinate> solve(Maze maze) {
        if (explore(maze, start.row(), start.col())
        ) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean explore(Maze maze, int row, int col) {
        if (!maze.isValidCoordinate(row, col)) {
            return false;
        }
        path.add(new Coordinate(row, col));
        maze.cellVisited(row, col);
        if (row == end.row() && col == end.col()) {
            return true;
        }

        if (maze.isValidCoordinate(row, col + 2)) {
            if (maze.getGrid()[row][col + 2].getType() != TypeCell.WALL) {
                if (explore(maze, row + DIRECTIONS[0][0], col + DIRECTIONS[0][1])) {
                    return true;
                }
            }
        }

        if (maze.getGrid()[row][col].getType() != TypeCell.FLOOR) {
            if (explore(maze, row + DIRECTIONS[1][0], col + DIRECTIONS[1][1])) {
                return true;
            }
        }

        if (maze.isValidCoordinate(row, col - 2)) {
            if (maze.getGrid()[row][col - 2].getType() != TypeCell.WALL) {
                if (explore(maze, row + DIRECTIONS[2][0], col + DIRECTIONS[2][1])) {
                    return true;
                }
            }
        }

        if (maze.isValidCoordinate(row - 1, col)) {
            if (maze.getGrid()[row - 1][col].getType() != TypeCell.FLOOR) {
                if (explore(maze, row + DIRECTIONS[3][0], col + DIRECTIONS[3][1])) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}

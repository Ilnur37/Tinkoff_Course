package edu.project2.solver;

import edu.project2.Maze;
import edu.project2.cells.Coordinate;
import edu.project2.cells.TypeCell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolverDFS extends Solver {
    private final List<Coordinate> path = new ArrayList<>();

    public SolverDFS(Coordinate start, Coordinate end) {
        super(start, end);
    }

    @Override
    public List<Coordinate> solve(Maze maze) {
        if (explore(maze, start.row(), start.col())) {
            cleanFieldIsVisited(maze);
            return path;
        }
        return Collections.emptyList();
    }

    private boolean explore(Maze maze, int row, int col) {
        if (maze.isInvalidCoordinate(row, col) || maze.isVisitedCoordinate(row, col)) {
            return false;
        }

        path.add(new Coordinate(row, col));
        maze.getGrid()[row][col].setVisited(true);
        if (isEnd(row, col)) {
            return true;
        }

        int dirInx = 0;
        for (Coordinate coordinate : CORD_FOR_CHECK_BORDER) {
            int biasRow = coordinate.row();
            int biasCol = coordinate.col();
            TypeCell forbiddenType = biasCol == 0 ? TypeCell.FLOOR : TypeCell.WALL;
            if (maze.getGrid()[row + biasRow][col + biasCol].getType() != forbiddenType) {
                if (explore(maze, row + DIRECTIONS[dirInx][0], col + DIRECTIONS[dirInx][1])) {
                    return true;
                }
            }
            ++dirInx;
        }

        path.remove(path.size() - 1);
        return false;
    }
}

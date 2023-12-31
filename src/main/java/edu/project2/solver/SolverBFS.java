package edu.project2.solver;

import edu.project2.Maze;
import edu.project2.cells.Coordinate;
import edu.project2.cells.LinkedCoordinate;
import edu.project2.cells.TypeCell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SolverBFS extends Solver {

    public SolverBFS(Coordinate start, Coordinate end) {
        super(start, end);
    }

    @Override
    public List<Coordinate> solve(Maze maze) {
        LinkedList<LinkedCoordinate> nextToVisit = new LinkedList<>();
        nextToVisit.add(new LinkedCoordinate(start, null));

        while (!nextToVisit.isEmpty()) {
            LinkedCoordinate current = nextToVisit.remove();
            int row = current.coordinate().row();
            int col = current.coordinate().col();

            if (maze.isInvalidCoordinate(row, col) || maze.isVisitedCoordinate(row, col)) {
                continue;
            }
            maze.getGrid()[row][col].setVisited(true);
            if (isEnd(row, col)) {
                cleanFieldIsVisited(maze);
                return backtrackPath(current);
            }

            int dirInx = 0;
            for (Coordinate coordinate : CORD_FOR_CHECK_BORDER) {
                int biasRow = coordinate.row();
                int biasCol = coordinate.col();
                TypeCell forbiddenType = biasCol == 0 ? TypeCell.FLOOR : TypeCell.WALL;
                if (maze.getGrid()[row + biasRow][col + biasCol].getType() != forbiddenType) {
                    LinkedCoordinate newCoordinate = new LinkedCoordinate(
                        new Coordinate(
                            row + DIRECTIONS[dirInx][0],
                            col + DIRECTIONS[dirInx][1]
                        ),
                        current
                    );
                    nextToVisit.add(newCoordinate);
                }
                ++dirInx;
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinate> backtrackPath(LinkedCoordinate cur) {
        List<Coordinate> path = new ArrayList<>();
        LinkedCoordinate iter = cur;

        while (iter != null) {
            path.add(iter.coordinate());
            iter = iter.parent();
        }

        return path;
    }
}

package edu.project2.solver;

import edu.project2.Maze;
import edu.project2.cells.Coordinate;
import edu.project2.cells.LinkedCoordinate;
import edu.project2.cells.TypeCell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SolverBFSImpl extends Solver {

    public SolverBFSImpl(Coordinate start, Coordinate end) {
        super(start, end);
    }

    @Override
    public List<Coordinate> solve(Maze maze) {
        LinkedList<LinkedCoordinate> nextToVisit = new LinkedList<>();
        nextToVisit.add(new LinkedCoordinate(start, null));

        while (!nextToVisit.isEmpty()) {
            LinkedCoordinate current = nextToVisit.remove();
            int row = current.getCoordinate().getRow();
            int col = current.getCoordinate().getCol();

            if (maze.isInvalidCoordinate(row, col)) {
                continue;
            }
            maze.getGrid()[row][col].setVisited(true);
            if (isEnd(row, col)) {
                cleanFieldIsVisited(maze);
                return backtrackPath(current);
            }

            int dirInx = 0;
            for (Coordinate coordinate : coordForCheckBorder) {
                int biasRow = coordinate.getRow();
                int biasCol = coordinate.getCol();
                TypeCell forbiddenType = biasCol == 0 ? TypeCell.FLOOR : TypeCell.WALL;
                if (maze.getGrid()[row + biasRow][col + biasCol].getType() != forbiddenType) {
                    LinkedCoordinate newCoordinate = new LinkedCoordinate(
                        new Coordinate(
                            row + directions[dirInx][0],
                            col + directions[dirInx][1]
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
            path.add(iter.getCoordinate());
            iter = iter.getParent();
        }

        return path;
    }
}

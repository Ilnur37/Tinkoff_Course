package edu.project2;

import edu.project2.cells.Cell;
import edu.project2.cells.Coordinate;
import edu.project2.generator.GeneratorByEllerAlgorithm;
import edu.project2.solver.SolverBFS;
import edu.project2.solver.SolverDFS;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project2.Printer.printSymbol;

public class Project2Test {
    private static final String PATH_LOWER_BORDER = "src/test/resources/project2/LowerBorder";
    private static final String PATH_RIGHT_BORDER = "src/test/resources/project2/RightBorder";

    @Test
    @DisplayName("Решение BFS находит путь в заранее известном лабиринте")
    void theSolutionFindsPathBFSInMaze() {
        GeneratorByEllerAlgorithm generator = new GeneratorByEllerAlgorithm();
        int[][] lower = FileReaderForTest.readingBorderFile(PATH_LOWER_BORDER);
        int[][] right = FileReaderForTest.readingBorderFile(PATH_RIGHT_BORDER);

        Maze maze = generator.createMaze(right, lower);

        for (int startX = 1; startX < 20; startX++) {
            for (int startY = 1; startY < 20; startY++) {
                for (int endX = 1; endX < 20; endX++) {
                    for (int endY = 1; endY < 20; endY++) {
                        SolverBFS solverBFS =
                            new SolverBFS(new Coordinate(startX, startY), new Coordinate(endX, endY));
                        List<Coordinate> pathBFS = solverBFS.solve(maze);

                        Assertions.assertFalse(pathBFS.isEmpty());
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Решение DFS находит путь в заранее известном лабиринте")
    void theSolutionFindsPathDFSInMaze() {
        GeneratorByEllerAlgorithm generator = new GeneratorByEllerAlgorithm();
        int[][] lower = FileReaderForTest.readingBorderFile(PATH_LOWER_BORDER);
        int[][] right = FileReaderForTest.readingBorderFile(PATH_RIGHT_BORDER);

        Maze maze = generator.createMaze(right, lower);

        for (int startX = 1; startX < 20; startX++) {
            for (int startY = 1; startY < 20; startY++) {
                for (int endX = 1; endX < 20; endX++) {
                    for (int endY = 1; endY < 20; endY++) {
                        SolverDFS solverDFS =
                            new SolverDFS(new Coordinate(startX, startY), new Coordinate(endX, endY));
                        List<Coordinate> pathDFS = solverDFS.solve(maze);

                        Assertions.assertFalse(pathDFS.isEmpty());
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Отрисовка заранее известного лабиринта")
    void renderingMatchesExpected() {
        GeneratorByEllerAlgorithm generator = new GeneratorByEllerAlgorithm();
        int[][] lower = FileReaderForTest.readingBorderFile(PATH_LOWER_BORDER);
        int[][] right = FileReaderForTest.readingBorderFile(PATH_RIGHT_BORDER);

        Maze maze = generator.createMaze(right, lower);

        try {
            Scanner scanner = new Scanner(new File("src/test/resources/project2/Maze20x20"));
            for (Cell[] cells : maze.getGrid()) {
                StringBuilder str = new StringBuilder();
                for (Cell cell : cells) {
                    str.append(printSymbol(cell.getType()));
                }
                Assertions.assertEquals(str.toString(), scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

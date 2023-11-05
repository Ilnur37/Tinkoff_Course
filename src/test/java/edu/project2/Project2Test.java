package edu.project2;

import edu.project2.cells.Cell;
import edu.project2.cells.Coordinate;
import edu.project2.generator.GeneratorByEllerAlgorithm;
import edu.project2.solver.SolverBFSImpl;
import edu.project2.solver.SolverDFSImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project2.Printer.printSymbol;

public class Project2Test {
    @Test
    @DisplayName("Решение находит путь в заранее известном лабиринте")
    void theSolutionFindsPathInMaze() {
        GeneratorByEllerAlgorithm generator = new GeneratorByEllerAlgorithm();
        int[][] lower = new int[20][20];
        int[][] right = new int[20][20];
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/project2/RightBorder"));
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    right[i][j] = scanner.nextInt();
                }
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/project2/LowerBorder"));

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    lower[i][j] = scanner.nextInt();
                }
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Maze maze = generator.createMaze(right, lower);

        for (int startX = 1; startX < 20; startX++) {
            for (int startY = 1; startY < 20; startY++) {
                for (int endX = 1; endX < 20; endX++) {
                    for (int endY = 1; endY < 20; endY++) {
                        SolverBFSImpl solverBFS =
                            new SolverBFSImpl(new Coordinate(startX, startY), new Coordinate(endX, endY));
                        List<Coordinate> path = solverBFS.solve(maze);

                        Assertions.assertFalse(path.isEmpty());
                    }
                }
            }
        }

        for (int startX = 1; startX < 20; startX++) {
            for (int startY = 1; startY < 20; startY++) {
                for (int endX = 1; endX < 20; endX++) {
                    for (int endY = 1; endY < 20; endY++) {
                        SolverDFSImpl solverDFS =
                            new SolverDFSImpl(new Coordinate(startX, startY), new Coordinate(endX, endY));
                        List<Coordinate> path = solverDFS.solve(maze);

                        Assertions.assertFalse(path.isEmpty());
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Отрисовка заранее известного лабиринта")
    void renderingMatchesExpected() {
        GeneratorByEllerAlgorithm generator = new GeneratorByEllerAlgorithm();
        int[][] lower = new int[20][20];
        int[][] right = new int[20][20];
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/project2/RightBorder"));
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    right[i][j] = scanner.nextInt();
                }
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/project2/LowerBorder"));

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    lower[i][j] = scanner.nextInt();
                }
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Maze maze = generator.createMaze(right, lower);

        try {
            Scanner scanner = new Scanner(new File("src/main/resources/project2/Maze20x20"));
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

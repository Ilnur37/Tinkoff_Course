package edu.project2;

import edu.project2.cells.Coordinate;
import edu.project2.generator.GeneratorByEllerAlgorithm;
import edu.project2.solver.SolverBFSImpl;
import edu.project2.solver.SolverDFSImpl;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class AppRunner {
    private static final int MAX_SIZE = 30;
    private static final String AXIS_X = "По оси X: ";
    private static final String AXIS_Y = "По оси Y: ";
    private static final int ALL_SOLUTIONS_METHODS = 3;

    private AppRunner() {

    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        int row = 0;
        int col = 0;
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        int typeSolver = 0;

        Scanner in = new Scanner(System.in);
        while (isNegative(row) || isInvalidSize(row)) {
            System.out.println("Введите высоту лабиринта:");
            row = in.nextInt();
        }
        while (isNegative(col) || isInvalidSize(col)) {
            System.out.println("Введите ширину лабиринта:");
            col = in.nextInt();
        }

        System.out.println("Генерация лабиринта " + row + "X" + col + " с помощью алгоритма Эллера");
        GeneratorByEllerAlgorithm generator = new GeneratorByEllerAlgorithm();
        Maze maze = generator.generate(row, col);
        Printer.printMaze(maze);

        System.out.println("\nВыберите точку старта");
        while (isInvalidPoint(startX, row)) {
            System.out.println(AXIS_X);
            startX = in.nextInt();
        }
        while (isInvalidPoint(startY, row)) {
            System.out.println(AXIS_Y);
            startY = in.nextInt();
        }
        System.out.println("\nВыберите точку финиша");
        while (isInvalidPoint(endX, col)) {
            System.out.println(AXIS_X);
            endX = in.nextInt();
        }
        while (isInvalidPoint(endY, col)) {
            System.out.println(AXIS_Y);
            endY = in.nextInt();
        }

        while (isInvalidTypeSolver(typeSolver)) {
            System.out.println("Выберите метод решения лабиринта:\t1 - DFS;\t2 - BFS;\t3-Оба");
            typeSolver = in.nextInt();
        }
        switch (typeSolver) {
            case 1 -> {
                solveDFS(maze, startX, startY, endX, endY);
                break;
            }
            case 2 -> {
                solveBFS(maze, startX, startY, endX, endY);
                break;
            }
            default -> {
                solveDFS(maze, startX, startY, endX, endY);
                solveBFS(maze, startX, startY, endX, endY);
                break;
            }
        }
    }

    private static void solveDFS(Maze maze, int startX, int startY, int endX, int endY) {
        SolverDFSImpl solverDFS = new SolverDFSImpl(new Coordinate(startX, startY), new Coordinate(endX, endY));
        List<Coordinate> path = solverDFS.solve(maze);
        Printer.printMazeWithPath(maze, path);
    }

    private static void solveBFS(Maze maze, int startX, int startY, int endX, int endY) {
        SolverBFSImpl solverBFS = new SolverBFSImpl(new Coordinate(startX, startY), new Coordinate(endX, endY));
        List<Coordinate> path = solverBFS.solve(maze);
        Printer.printMazeWithPath(maze, path);
    }

    private static boolean isInvalidSize(int num) {
        return num > MAX_SIZE;
    }

    private static boolean isNegative(int num) {
        return num <= 0;
    }

    private static boolean isInvalidPoint(int point, int border) {
        return point > border || isNegative(point);
    }

    private static boolean isInvalidTypeSolver(int num) {
        return num != 1 && num != 2 && num != ALL_SOLUTIONS_METHODS;
    }
}

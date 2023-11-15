package edu.project2;

import edu.project2.cells.Coordinate;
import edu.project2.generator.GeneratorByEllerAlgorithm;
import edu.project2.solver.Solver;
import edu.project2.solver.SolverBFS;
import edu.project2.solver.SolverDFS;
import java.util.List;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@SuppressWarnings("RegexpSinglelineJava")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppRunner {
    private static final int ALL_SOLUTIONS_METHODS = 3;
    private static final int MAX_SIZE = 30;
    private static final String SIZE_MESSAGE = "Число должно быть больше 0 и меньше ";
    private static final String AXIS_X = "По оси X: ";
    private static final String AXIS_Y = "По оси Y: ";
    private static final Scanner IN = new Scanner(System.in);
    private static int row = 0;
    private static int col = 0;
    private static int startX = 0;
    private static int startY = 0;
    private static int endX = 0;
    private static int endY = 0;
    private static int solverType = 0;

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        Maze maze = createMaze();
        Printer.printMaze(maze);
        createStartAndEndPoint();
        enterSolverType();
        runSolver(maze);
    }

    private static Maze createMaze() {
        System.out.println("Введите высоту лабиринта:");
        row = enterSize(row);
        System.out.println("Введите ширину лабиринта:");
        col = enterSize(col);

        System.out.println("Генерация лабиринта " + row + "X" + col + " с помощью алгоритма Эллера");
        GeneratorByEllerAlgorithm generator = new GeneratorByEllerAlgorithm();
        return generator.generate(row, col);
    }

    private static int enterSize(int num) {
        int tempNum = num;
        while (isInvalidSize(tempNum)) {
            System.out.println(SIZE_MESSAGE + MAX_SIZE);
            tempNum = IN.nextInt();
        }
        return tempNum;
    }

    public static boolean isInvalidSize(int num) {
        return num > MAX_SIZE || num <= 0;
    }

    private static void createStartAndEndPoint() {
        System.out.println("\nВыберите точку старта");
        System.out.println(AXIS_X);
        startX = enterPoint(startX, col);

        System.out.println(AXIS_Y);
        startY = enterPoint(startY, row);

        System.out.println("\nВыберите точку финиша");
        System.out.println(AXIS_X);
        endX = enterPoint(endX, col);

        System.out.println(AXIS_Y);
        endY = enterPoint(endY, row);
    }

    private static int enterPoint(int point, int border) {
        int tempPoint = point;
        while (isInvalidPoint(tempPoint, border)) {
            System.out.println(SIZE_MESSAGE + border);
            tempPoint = IN.nextInt();
        }
        return tempPoint;
    }

    public static boolean isInvalidPoint(int point, int border) {
        return point > border || point <= 0;
    }

    private static void enterSolverType() {
        while (isInvalidTypeSolver(solverType)) {
            System.out.println("Выберите метод решения лабиринта:\t1 - DFS;\t2 - BFS;\t3-Оба");
            solverType = IN.nextInt();
        }
    }

    public static boolean isInvalidTypeSolver(int num) {
        return num != 1 && num != 2 && num != ALL_SOLUTIONS_METHODS;
    }

    private static void runSolver(Maze maze) {
        switch (solverType) {
            case 1 -> solveDFS(maze, startX, startY, endX, endY);
            case 2 -> solveBFS(maze, startX, startY, endX, endY);
            default -> {
                solveDFS(maze, startX, startY, endX, endY);
                solveBFS(maze, startX, startY, endX, endY);
            }
        }
    }

    private static void solveDFS(Maze maze, int startX, int startY, int endX, int endY) {
        Solver solverDFS = new SolverDFS(new Coordinate(startX, startY), new Coordinate(endX, endY));
        List<Coordinate> path = solverDFS.solve(maze);
        Printer.printMazeWithPath(maze, path);
    }

    private static void solveBFS(Maze maze, int startX, int startY, int endX, int endY) {
        Solver solverBFS = new SolverBFS(new Coordinate(startX, startY), new Coordinate(endX, endY));
        List<Coordinate> path = solverBFS.solve(maze);
        Printer.printMazeWithPath(maze, path);
    }
}

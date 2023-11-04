package edu.project2;

import edu.project2.cells.Coordinate;
import edu.project2.generator.GeneratorByEllerAlgorithm;
import edu.project2.solver.SolverBFSImpl;
import edu.project2.solver.SolverDFSImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GeneratorByEllerAlgorithm generator = new GeneratorByEllerAlgorithm();
        int row = 20;
        int col = 20;
        int st = 1;
        int end = row;
        Maze maze = generator.generate(row, col);
        Printer.printMaze(maze);
        SolverDFSImpl solverDFS = new SolverDFSImpl(new Coordinate(st, st), new Coordinate(end, end));
        List<Coordinate> ddd = solverDFS.solve(maze);
        Printer.printMazeWithPath(maze, ddd);
        SolverBFSImpl solverBFS = new SolverBFSImpl(new Coordinate(st, st), new Coordinate(end, end));
        List<Coordinate> bbb = solverBFS.solve(maze);
        Printer.printMazeWithPath(maze, bbb);
    }
}

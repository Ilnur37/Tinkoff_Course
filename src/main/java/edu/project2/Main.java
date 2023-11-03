package edu.project2;

import edu.project2.cells.Coordinate;
import edu.project2.generator.GeneratorByEllerAlgorithm;
import edu.project2.solver.SolverDFSImpl;

public class Main {
    public static void main(String[] args) {
        GeneratorByEllerAlgorithm generator = new GeneratorByEllerAlgorithm();
        Maze maze = generator.generate(40, 40);
        Printer.printMaze(maze);
        SolverDFSImpl solverDFS = new SolverDFSImpl(new Coordinate(1, 1), new Coordinate(40, 40));
        solverDFS.solve(maze);
        Printer.PrintMazeWithPath(maze, solverDFS.path);
    }
}

package edu.project2;

import edu.project2.generator.GeneratorByEllerAlgorithm;

public class Main {
    public static void main(String[] args) {
        GeneratorByEllerAlgorithm generator = new GeneratorByEllerAlgorithm();
        Maze maze = generator.generate(10, 10);
        maze.printMaze();
    }
}

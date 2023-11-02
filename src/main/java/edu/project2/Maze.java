package edu.project2;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public void printMaze() {
        for (int i = 0; i < grid.length; i++) {
            System.out.println();
            for (int j = 0; j < grid[0].length; j++) {
                switch (grid[i][j].type()) {
                    case PASSAGE -> System.out.print(" ");
                    case WALL -> System.out.print("|");
                    case FLOOR -> System.out.print("_");
                }
            }
        }
    }

}

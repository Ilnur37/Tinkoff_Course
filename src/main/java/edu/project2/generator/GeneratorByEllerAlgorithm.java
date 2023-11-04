package edu.project2.generator;

import edu.project2.Maze;
import edu.project2.cells.Cell;
import edu.project2.cells.TypeCell;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeneratorByEllerAlgorithm implements Generator {
    private int height;
    private int width;
    private final int emptyValue = 0;
    private final List<Integer> cellBelongSet = new ArrayList<>();
    private int countSet = 0;

    @Override
    public Maze generate(int height, int width) {
        this.height = height;
        this.width = width;

        int[][] rightBorder = new int[height][width];
        int[][] lowerBorder = new int[height][width];

        fillEmptyValue();
        for (int j = 0; j < height - 1; j++) {
            assignSet();
            createRightBorder(rightBorder, j);
            createLowerBorder(lowerBorder, j);
            checkLowerBorder(lowerBorder, j);
            preparatingNewLine(j, lowerBorder);
        }
        addingEndLine(rightBorder, lowerBorder);
        return createMaze(rightBorder, lowerBorder);
    }

    private void fillEmptyValue() {
        for (int i = 0; i < width; i++) {
            cellBelongSet.add(emptyValue);
        }
    }

    private void assignSet() {
        for (int i = 0; i < width; i++) {
            if (cellBelongSet.get(i) == emptyValue) {
                cellBelongSet.set(i, ++countSet);
            }
        }
    }

    private void createRightBorder(int[][] rightBorder, int row) {
        for (int i = 0; i < width - 1; i++) {
            int haveBorder = (int) (Math.random() * 2);

            if (haveBorder == 1 || Objects.equals(cellBelongSet.get(i), cellBelongSet.get(i + 1))) {
                rightBorder[row][i] = 1;
            } else {
                mergeSet(i, cellBelongSet.get(i));
            }
        }
        rightBorder[row][width - 1] = 1;
    }

    private void mergeSet(int idx, int numberSet) {
        int mutableSet = cellBelongSet.get(idx + 1);
        for (int j = 0; j < width; j++) {
            if (cellBelongSet.get(j) == mutableSet) {
                cellBelongSet.set(j, numberSet);
            }
        }
    }

    private void createLowerBorder(int[][] lowerBorder, int row) {
        for (int i = 0; i < width; i++) {
            int haveBorder = (int) (Math.random() * 2);

            int set = cellBelongSet.get(i);
            int countElementInSet = (int) cellBelongSet.stream()
                .filter(cell -> Objects.equals(cell, set))
                .count();

            if (haveBorder == 1 && countElementInSet != 1) {
                lowerBorder[row][i] = 1;
            }
        }
    }

    private void checkLowerBorder(int[][] lowerBorder, int row) {
        for (int i = 0; i < width; i++) {
            if (calculateLowerWalls(cellBelongSet.get(i), row, lowerBorder) == 0) {
                lowerBorder[row][i] = 0;
            }
        }
    }

    private int calculateLowerWalls(int element, int row, int[][] lowerBorder) {
        int countHorizontalWalls = 0;
        for (int i = 0; i < width; i++) {
            if (cellBelongSet.get(i) == element && lowerBorder[row][i] == 0) {
                countHorizontalWalls++;
            }
        }
        return countHorizontalWalls;
    }

    private void preparatingNewLine(int row, int[][] lowerBorder) {
        for (int i = 0; i < width; i++) {
            if (lowerBorder[row][i] == 1) {
                cellBelongSet.set(i, emptyValue);
            }
        }
    }

    private void addingEndLine(int[][] rightBorder, int[][] lowerBorder) {
        assignSet();
        createRightBorder(rightBorder, height - 1);
        checkedEndLine(rightBorder, lowerBorder);
    }

    private void checkedEndLine(int[][] rightBorder, int[][] lowerBorder) {
        for (int i = 0; i < width - 1; i++) {
            if (!Objects.equals(cellBelongSet.get(i), cellBelongSet.get(i + 1))) {
                rightBorder[height - 1][i] = 0;
                mergeSet(i, cellBelongSet.get(i));
            }
            lowerBorder[height - 1][i] = 1;
        }
        lowerBorder[height - 1][width - 1] = 1;
    }

    @SuppressWarnings("MagicNumber")
    private Maze createMaze(int[][] rightBorder, int[][] lowerBorder) {
        int heightCells = height + 1;
        int widthCells = width * 4 + 1;
        Cell[][] cells = new Cell[heightCells][widthCells];

        for (int i = 0; i < widthCells; i++) {
            cells[0][i] = new Cell(0, i, TypeCell.FLOOR);
            cells[heightCells - 1][i] = new Cell(heightCells - 1, i, TypeCell.FLOOR);
        }
        for (int i = 1; i < heightCells; i++) {
            cells[i][0] = new Cell(i, 0, TypeCell.WALL);
            cells[i][widthCells - 1] = new Cell(i, widthCells - 1, TypeCell.WALL);
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (rightBorder[i][j] == 1) {
                    cells[i + 1][(j + 1) * 3 + j + 1] = new Cell(i + 1, (j + 1) * 3 + j + 1, TypeCell.WALL);
                }
                if (lowerBorder[i][j] == 1) {
                    if (cells[i + 1][j * 3 + j] == null) {
                        cells[i + 1][j * 3 + j] = new Cell(i + 1, j * 3 + j, TypeCell.FLOOR);
                    }
                    cells[i + 1][j * 3 + j + 1] = new Cell(i + 1, j * 3 + j + 1, TypeCell.FLOOR);
                    cells[i + 1][j * 3 + j + 2] = new Cell(i + 1, j * 3 + j + 2, TypeCell.FLOOR);
                    cells[i + 1][j * 3 + j + 3] = new Cell(i + 1, j * 3 + j + 3, TypeCell.FLOOR);
                    if (cells[i + 1][j * 3 + j + 4] == null) {
                        cells[i + 1][j * 3 + j + 4] = new Cell(i + 1, j * 3 + j + 4, TypeCell.FLOOR);
                    }
                }
            }
        }

        for (int i = 1; i < heightCells - 1; i++) {
            for (int j = 1; j < widthCells - 1; j++) {
                if (cells[i][j] == null) {
                    cells[i][j] = new Cell(i, j, TypeCell.PASSAGE);
                }
            }
        }
        return new Maze(heightCells, widthCells, cells);
    }
}

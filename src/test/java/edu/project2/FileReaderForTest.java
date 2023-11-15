package edu.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderForTest {
    private static final String PATH_LOWER_BORDER = "src/test/resources/project2/LowerBorder";
    private static final String PATH_RIGHT_BORDER = "src/test/resources/project2/RightBorder";

    public static int[][] readingBorderFile(String path) {
        int[][] lower = new int[20][20];
        try {
            Scanner scanner = new Scanner(new File(path));
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    lower[i][j] = scanner.nextInt();
                }
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lower;
    }
}

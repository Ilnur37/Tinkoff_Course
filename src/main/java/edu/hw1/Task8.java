package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task8 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task8() {

    }

    private static void validateBoard(int[][] board) {
        LOGGER.info("****************************");
        for (int i = 0; i < board.length; i++) {
            LOGGER.info(Arrays.toString(board[i]));
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 1 && board[i][j] != 0) {
                    throw new RuntimeException("Board values must be 0 or 1!");
                }
            }
        }
    }

    @SuppressWarnings("MagicNumber")
    private static boolean canTakeTheHorse(int[][] board, int i, int j) {
        int steps = 8;
        int[] cordI = new int[] {-1, 1, 2, 2, 1, -1, -2, -2};
        int[] cordJ = new int[] {2, 2, 1, -1, -2, -2, -1, 1};

        for (int step = 0; step < steps; step++) {
            if (i + cordI[step] < 0 || j + cordJ[step] < 0
                || i + cordI[step] > 7 || j + cordJ[step] > 7) {
                continue;
            }
            if (board[i + cordI[step]][j + cordJ[step]] == 1) {
                return true;
            }
        }

        return false;
    }

    public static boolean knightBoardCapture(int[][] board) {
        validateBoard(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    if (canTakeTheHorse(board, i, j)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}

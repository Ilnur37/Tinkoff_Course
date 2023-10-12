package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task8 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int BOARD_SIZE = 8;
    private final static int NUMBER_OF_POSSIBLE_KNIGHT_MOVES = 8;
    private final static int BOTTOM_BORDER_OF_THE_BOARD = 0;
    private final static int TOP_BORDER_OF_THE_BOARD = NUMBER_OF_POSSIBLE_KNIGHT_MOVES - 1;

    private Task8() {

    }

    private static void validateBoard(int[][] board) {
        LOGGER.info("****************************");
        for (int[] ints : board) {
            LOGGER.info(Arrays.toString(ints));
        }
        RuntimeException exceptionBoardSize = new RuntimeException("The board should be 8*8 in size!");
        if (board.length != BOARD_SIZE) {
            throw exceptionBoardSize;
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i].length != BOARD_SIZE) {
                throw exceptionBoardSize;
            }
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != 1 && board[i][j] != 0) {
                    throw new RuntimeException("Board values must be 0 or 1!");
                }
            }
        }
    }

    @SuppressWarnings("MagicNumber")
    private static boolean canTakeTheHorse(int[][] board, int i, int j) {
        int[] cordI = new int[] {-1, 1, 2, 2, 1, -1, -2, -2};
        int[] cordJ = new int[] {2, 2, 1, -1, -2, -2, -1, 1};

        for (int step = 0; step < NUMBER_OF_POSSIBLE_KNIGHT_MOVES; step++) {
            if (i + cordI[step] < BOTTOM_BORDER_OF_THE_BOARD || j + cordJ[step] < BOTTOM_BORDER_OF_THE_BOARD
                || i + cordI[step] > TOP_BORDER_OF_THE_BOARD || j + cordJ[step] > TOP_BORDER_OF_THE_BOARD) {
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
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
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

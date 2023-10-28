package edu.project1.session;

import edu.project1.AnswerResult;
import edu.project1.ConsoleMessages;
import edu.project1.UserResult.EndGame;
import edu.project1.UserResult.InvalidAnswer;
import edu.project1.UserResult.LetterGuessed;
import edu.project1.UserResult.UserResult;
import edu.project1.difficulty.DifficultyManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionManager {
    private static final String CLOSE_SESSION_WHEN_LENGTH_ANSWER_LESS_MIN_LEN = "-1";
    private static final String CLOSE_SESSION_CODE_TO_EXIT_THE_GAME = "END";
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ConsoleMessages CONSOLE_MESSAGES = new ConsoleMessages();
    private static final DifficultyManager DIFFICULTY_MANAGER = new DifficultyManager();
    private static final List<UserResult> CHECK_ANSWER =
        new ArrayList<>(Arrays.asList(new InvalidAnswer(), new LetterGuessed(), new EndGame()));

    private SessionManager() {

    }

    public static Session createGameSession(int level) {

        String answer = DIFFICULTY_MANAGER.getRandomWord(level);
        if (shouldCloseSession(answer)) {
            closeSession();
        }
        int maxAttempt = DIFFICULTY_MANAGER.getMaxAttempt(level);
        return new Session(answer, maxAttempt);
    }

    public static boolean isValidDifficultyLevel(String difficulty) {
        int minDifficulty = 1;
        int maxDifficulty = DIFFICULTY_MANAGER.getMaxDifficulty();
        if (shouldCloseSession(difficulty)) {
            closeSession();
        }
        for (int i = minDifficulty; i <= maxDifficulty; i++) {
            if (Integer.toString(i).equals(difficulty)) {
                return true;
            }
        }
        return false;
    }

    public static int getMaxDifficulty() {
        return DIFFICULTY_MANAGER.getMaxDifficulty();
    }

    public static boolean shouldCloseSession(String str) {
        return str.equals(CLOSE_SESSION_WHEN_LENGTH_ANSWER_LESS_MIN_LEN)
            || str.equals(CLOSE_SESSION_CODE_TO_EXIT_THE_GAME);
    }

    public static void closeSession() {
        System.exit(1);
    }

    public static boolean guessResult(Session session, String inputStr) {
        boolean inputStrIsInvalid = false;
        for (UserResult result : CHECK_ANSWER) {
            if (inputStrIsInvalid) {
                break;
            }
            AnswerResult answerResult = result.processResponse(session, inputStr);
            switch (answerResult) {
                case INVALID_ANSWER_LENGTH -> {
                    LOGGER.info(CONSOLE_MESSAGES.invalidAnswerLength);
                    inputStrIsInvalid = true;
                }
                case INVALID_ANSWER_CHAR -> {
                    LOGGER.info(CONSOLE_MESSAGES.invalidAnswerChar);
                    inputStrIsInvalid = true;
                }
                case INVALID_ANSWER_REPEAT_CHAR -> {
                    LOGGER.info(CONSOLE_MESSAGES.invalidAnswerRepeatChar);
                    inputStrIsInvalid = true;
                }
                case VALID_ANSWER -> {
                    session.addAnswer(inputStr.charAt(0));
                }

                case HIT -> {
                    LOGGER.info(CONSOLE_MESSAGES.hit);
                    session.incrementAttempts();
                    session.removeCharFromAns(inputStr.charAt(0));
                }
                case NOT_HIT -> {
                    LOGGER.info(CONSOLE_MESSAGES.notHit);
                    session.incrementAttempts();
                }

                case WIN -> {
                    LOGGER.info(CONSOLE_MESSAGES.win);
                    LOGGER.info("Ответ: " + session.getAnswer());
                    return true;
                }
                case DEFEAT -> {
                    LOGGER.info(CONSOLE_MESSAGES.defeat);
                    LOGGER.info("Ответ был: " + session.getAnswer());
                    return true;
                }
                case NEXT -> LOGGER.info("");
                default -> throw new RuntimeException("Function .responseProcessing return unknowing parameter!");
            }
        }
        return false;
    }
}

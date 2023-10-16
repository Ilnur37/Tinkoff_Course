package edu.project1;

import edu.project1.UserResult.EndGame;
import edu.project1.UserResult.InvalidAnswer;
import edu.project1.UserResult.LetterGuessed;
import edu.project1.UserResult.UserResult;
import edu.project1.difficulty.DifficultyLevel;
import edu.project1.difficulty.EasyLevel;
import edu.project1.difficulty.HardLevel;
import edu.project1.difficulty.MiddleLevel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Console {
    private static final String INVALID_ANSWER_LENGTH = "Вы должны ввести один символ";
    private static final String INVALID_ANSWER_CHAR = "Вы должны ввести прописную букву русского алфавита";
    private static final String INVALID_ANSWER_REPEAT_CHAR = "Вы уже вводили эту букву";
    private static final String NOT_HIT = "Промах";
    private static final String HIT = "Попадание!";
    private static final String WIN = "Вы победили";
    private static final String DEFEAT = "Вы проиграли";
    private static final String ENTER_THE_NUMBER = "Введите число от 1 до 3";
    public static final int MIN_DIFFICULTY = 1;
    public static final int MAX_DIFFICULTY = 3;

    private final static Logger LOGGER = LogManager.getLogger();
    private final Scanner in = new Scanner(System.in);
    private final List<UserResult> checkAnswer =
        new ArrayList<>(Arrays.asList(new InvalidAnswer(), new LetterGuessed(), new EndGame()));

    public void run() {
        Session session = chooseDifficulty();
        boolean endGame = false;

        while (!endGame) {
            LOGGER.info("Слово: " + getSecurityWord(session));
            LOGGER.info("Попытка " + session.getAttempts() + " из " + session.getMaxAttempts());
            LOGGER.info("Введите букву: ");
            String inputStr = in.nextLine();
            if (Session.shouldCloseSession(inputStr)) {
                Session.closeSession();
            }
            endGame = guessResult(session, inputStr);
        }
    }

    private Session chooseDifficulty() {
        LOGGER.info("Если хотите выйти из игры введите: 'END'");
        LOGGER.info("Если знаете слово, можете ввести его сразу");
        LOGGER.info("Выберите сложность игры (1 - Легая, 2 - Средняя, 3 - Сложная)");
        String level = " ";
        while (!validDifficulty(level)) {
            LOGGER.info(ENTER_THE_NUMBER);
            level = in.nextLine();
        }

        List<DifficultyLevel> difficultyLevels = new ArrayList<>(MAX_DIFFICULTY);
        difficultyLevels.add(new EasyLevel());
        difficultyLevels.add(new MiddleLevel());
        difficultyLevels.add(new HardLevel());
        return new Session(difficultyLevels.get(Integer.parseInt(level) - 1));
    }

    public boolean validDifficulty(String difficulty) {
        if (Session.shouldCloseSession(difficulty)) {
            Session.closeSession();
        }
        for (int i = MIN_DIFFICULTY; i <= MAX_DIFFICULTY; i++) {
            if (Integer.toString(i).equals(difficulty)) {
                return true;
            }
        }
        return false;
    }

    public boolean guessResult(Session session, String inputStr) {
        boolean inputStrIsInvalid = false;
        for (UserResult result : checkAnswer) {
            if (inputStrIsInvalid) {
                break;
            }
            AnswerResult answerResult = result.responseProcessing(session, inputStr);
            switch (answerResult) {
                case INVALID_ANSWER_LENGTH -> {
                    LOGGER.info(INVALID_ANSWER_LENGTH);
                    inputStrIsInvalid = true;
                }
                case INVALID_ANSWER_CHAR -> {
                    LOGGER.info(INVALID_ANSWER_CHAR);
                    inputStrIsInvalid = true;
                }
                case INVALID_ANSWER_REPEAT_CHAR -> {
                    LOGGER.info(INVALID_ANSWER_REPEAT_CHAR);
                    inputStrIsInvalid = true;
                }
                case VALID_ANSWER -> {
                    session.addAnswer(inputStr.charAt(0));
                }

                case HIT -> {
                    LOGGER.info(HIT);
                    //session.incrementCountHit();
                    session.incrementAttempts();
                    session.removeCharFromAns(inputStr.charAt(0));
                }
                case NOT_HIT -> {
                    LOGGER.info(NOT_HIT);
                    session.incrementAttempts();
                }

                case WIN -> {
                    LOGGER.info(WIN);
                    LOGGER.info("Ответ: " + session.getAnswer());
                    return true;
                }
                case DEFEAT -> {
                    LOGGER.info(DEFEAT);
                    LOGGER.info("Ответ был: " + session.getAnswer());
                    return true;
                }
                case NEXT -> LOGGER.info("");
                default -> throw new RuntimeException("Function .responseProcessing return unknowing parameter!");
            }
        }
        return false;
    }

    public String getSecurityWord(Session session) {
        String answer = session.getAnswer();
        StringBuilder securityWord = new StringBuilder();
        for (char ch : answer.toCharArray()) {
            if (session.getDifferentLettersInAnswer().contains(ch)) {
                securityWord.append('*');
            } else {
                securityWord.append(ch);
            }
        }
        return securityWord.toString();
    }
}

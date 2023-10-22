package edu.project1;

import edu.project1.session.Session;
import edu.project1.session.SessionManager;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Console {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Scanner in = new Scanner(System.in);

    public void run() {
        int difficultyLevel = chooseDifficulty();
        Session session = SessionManager.createGameSession(difficultyLevel);
        boolean endGame = false;

        while (!endGame) {
            LOGGER.info("Слово: " + getMaskedWord(session));
            LOGGER.info("Попытка " + session.getAttempts() + " из " + session.getMaxAttempts());
            LOGGER.info("Введите букву: ");
            String inputStr = in.nextLine();
            if (SessionManager.shouldCloseSession(inputStr)) {
                SessionManager.closeSession();
            }
            endGame = SessionManager.guessResult(session, inputStr);
        }
    }

    private int chooseDifficulty() {
        LOGGER.info("Если хотите выйти из игры, введите: 'END' ");
        LOGGER.info("Выберите сложность игры:");
        String level = " ";
        while (!SessionManager.isValidDifficultyLevel(level)) {
            LOGGER.info("Введите число от 1 до " + SessionManager.getMaxDifficulty());
            level = in.nextLine();
        }
        return Integer.parseInt(level);
    }

    public String getMaskedWord(Session session) {
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

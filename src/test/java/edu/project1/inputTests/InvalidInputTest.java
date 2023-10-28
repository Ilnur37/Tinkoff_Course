package edu.project1.inputTests;

import edu.project1.Console;
import edu.project1.session.Session;
import edu.project1.session.SessionManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidInputTest {

    @ParameterizedTest
    @DisplayName("Выбор уровня сложности(некорректные данные)")
    @ValueSource(strings = {"12321", "rf", "e", "0", "4"})
    void validDifficulty(String word) {
        assertFalse(SessionManager.isValidDifficultyLevel(word));
    }

    @ParameterizedTest
    @DisplayName("Угадывание буквы(некорректные данные)")
    @ValueSource(strings = {"erer", "d", "1"})
    void responseProcessing(String word) {
        Set<Character> differentLettersInAnswer1 = new HashSet<>(List.of('с', 'т', 'о', 'л'));
        Set<Character> differentLettersInAnswer2 = new HashSet<>(List.of('с', 'т', 'о', 'л'));
        Session session = new Session(
            "стол",
            5,
            differentLettersInAnswer1,
            new ArrayList<>(),
            0
        );
        Session changedSession = new Session(
            "стол",
            5,
            differentLettersInAnswer2,
            new ArrayList<>(),
            0
        );

        SessionManager.guessResult(changedSession, word);

        assertEquals(session, changedSession);
    }

    @Test
    @DisplayName("Угадывание буквы(повторный ввод буквы)")
    void responseProcessing_whenRepeat() {
        String answer = "т";
        Set<Character> differentLettersInAnswer1 = new HashSet<>(List.of('с', 'о', 'л'));
        Set<Character> differentLettersInAnswer2 = new HashSet<>(List.of('с', 'о', 'л'));
        Session session = new Session(
            "стол",
            5,
            differentLettersInAnswer1,
            new ArrayList<>(List.of(answer.charAt(0))),
            1
        );
        Session changedSession = new Session(
            "стол",
            5,
            differentLettersInAnswer2,
            new ArrayList<>(List.of(answer.charAt(0))),
            1
        );

        SessionManager.guessResult(changedSession, answer);

        assertEquals(session, changedSession);
    }
}

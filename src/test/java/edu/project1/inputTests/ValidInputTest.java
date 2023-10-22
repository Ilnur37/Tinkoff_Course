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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidInputTest {
    private final Set<Character> differentLettersInAnswer = new HashSet<>(List.of('с', 'т', 'о', 'л'));
    private final Session changedSession = new Session(
        "стол",
        5,
        differentLettersInAnswer,
        new ArrayList<>(),
        0
    );

    @ParameterizedTest
    @DisplayName("Выбор уровня сложности(корректные данные)")
    @ValueSource(strings = {"1", "2", "3"})
    void validDifficulty(String word) {
        assertTrue(SessionManager.isValidDifficultyLevel(word));
    }

    @Test
    @DisplayName("Угадывание буквы(попал)")
    void responseProcessing_whenHit() {
        String answer = "т";
        Set<Character> differentLettersInAnswer1 = new HashSet<>(List.of('с', 'о', 'л'));
        Session session = new Session(
            "стол",
            5,
            differentLettersInAnswer1,
            new ArrayList<>(List.of(answer.charAt(0))),
            1
        );

        SessionManager.guessResult(changedSession, answer);
        assertEquals(session, changedSession);
    }

    @Test
    @DisplayName("Угадывание буквы(не попал)")
    void responseProcessing_whenNotHit() {
        String answer = "я";
        Set<Character> differentLettersInAnswer1 = new HashSet<>(List.of('с', 'т', 'о', 'л'));
        Session session = new Session(
            "стол",
            5,
            differentLettersInAnswer1,
            new ArrayList<>(List.of(answer.charAt(0))),
            1
        );

        SessionManager.guessResult(changedSession, answer);
        assertEquals(session, changedSession);
    }

    @DisplayName("Угадывание буквы(победа)")
    @Test
    void responseProcessing_whenWin() {
        SessionManager.guessResult(changedSession, "с");
        SessionManager.guessResult(changedSession, "т");
        SessionManager.guessResult(changedSession, "о");
        assertTrue(SessionManager.guessResult(changedSession, "л"));
    }

    @DisplayName("Угадывание буквы(поражение)")
    @Test
    void responseProcessing_whenDefeat() {
        SessionManager.guessResult(changedSession, "а");
        SessionManager.guessResult(changedSession, "б");
        SessionManager.guessResult(changedSession, "ы");
        SessionManager.guessResult(changedSession, "с");
        assertTrue(SessionManager.guessResult(changedSession, "л"));
    }
}

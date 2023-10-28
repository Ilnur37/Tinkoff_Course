package edu.project1.responseProcessing;

import edu.project1.AnswerResult;
import edu.project1.Console;
import edu.project1.session.Session;
import edu.project1.UserResult.EndGame;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import edu.project1.session.SessionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinOrDefeatTest {
    Set<Character> differentLettersInAnswer1 = new HashSet<>(List.of('л'));
    Session session = new Session(
        "стол",
        5,
        differentLettersInAnswer1,
        new ArrayList<>(List.of('с', 'т', 'о', 'ю')),
        4
    );
    EndGame endGame = new EndGame();

    @Test
    @DisplayName("Enum: WIN")
    void responseProcessing_whenWIN() {
        String answer = "л";
        AnswerResult res = AnswerResult.WIN;

        SessionManager.guessResult(session, answer);
        assertEquals(res, endGame.processResponse(session, answer));
    }

    @Test
    @DisplayName("Enum: DEFEAT")
    void responseProcessing_whenDEFEAT() {
        String answer = "к";
        AnswerResult res = AnswerResult.DEFEAT;

        SessionManager.guessResult(session, answer);
        assertEquals(res, endGame.processResponse(session, answer));
    }
}

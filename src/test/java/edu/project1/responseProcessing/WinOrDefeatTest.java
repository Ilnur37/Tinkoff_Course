package edu.project1.responseProcessing;

import edu.project1.AnswerResult;
import edu.project1.Console;
import edu.project1.Session;
import edu.project1.UserResult.EndGame;
import edu.project1.UserResult.LetterGuessed;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        Console console = new Console();
        String answer = "л";
        AnswerResult res = AnswerResult.WIN;

        console.guessResult(session, answer);
        assertEquals(res, endGame.responseProcessing(session, answer));
    }

    @Test
    @DisplayName("Enum: DEFEAT")
    void responseProcessing_whenDEFEAT() {
        Console console = new Console();
        String answer = "к";
        AnswerResult res = AnswerResult.DEFEAT;

        console.guessResult(session, answer);
        assertEquals(res, endGame.responseProcessing(session, answer));
    }
}

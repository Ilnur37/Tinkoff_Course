package edu.project1.responseProcessing;

import edu.project1.AnswerResult;
import edu.project1.Console;
import edu.project1.session.Session;
import edu.project1.UserResult.InvalidAnswer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import edu.project1.session.SessionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidOrInvalidEnumTest {
    Set<Character> differentLettersInAnswer1 = new HashSet<>(List.of('с', 'т', 'о', 'л'));
    Session session = new Session(
        "стол",
        5,
        differentLettersInAnswer1,
        new ArrayList<>(),
        0
    );
    InvalidAnswer invalidAnswer = new InvalidAnswer();

    @Test
    @DisplayName("Enum: INVALID_ANSWER_LENGTH")
    void responseProcessing_whenINVALID_ANSWER_LENGTH() {
        String answer = "we";
        AnswerResult res = AnswerResult.INVALID_ANSWER_LENGTH;

        assertEquals(res, invalidAnswer.processResponse(session, answer));
    }

    @ParameterizedTest
    @DisplayName("Enum: INVALID_ANSWER_CHAR")
    @ValueSource(strings = {"w", "2", "!"})
    void responseProcessing_whenINVALID_ANSWER_CHAR(String answer) {
        AnswerResult res = AnswerResult.INVALID_ANSWER_CHAR;

        assertEquals(res, invalidAnswer.processResponse(session, answer));
    }

    @Test
    @DisplayName("Enum: INVALID_ANSWER_REPEAT_CHAR")
    void responseProcessing_whenINVALID_ANSWER_REPEAT_CHAR() {
        String answer = "в";
        AnswerResult res = AnswerResult.INVALID_ANSWER_REPEAT_CHAR;
        SessionManager.guessResult(session, answer);
        SessionManager.guessResult(session, answer);

        assertEquals(res, invalidAnswer.processResponse(session, answer));
    }
}

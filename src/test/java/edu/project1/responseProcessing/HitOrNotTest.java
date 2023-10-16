package edu.project1.responseProcessing;

import edu.project1.AnswerResult;
import edu.project1.Session;
import edu.project1.UserResult.LetterGuessed;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HitOrNotTest {
    Set<Character> differentLettersInAnswer1 = new HashSet<>(List.of('с', 'т', 'о', 'л'));
    Session session = new Session(
        "стол",
        5,
        differentLettersInAnswer1,
        new ArrayList<>(),
        0
    );
    LetterGuessed letterGuessed = new LetterGuessed();

    @ParameterizedTest
    @DisplayName("Enum: HIT")
    @ValueSource(strings = {"с", "т", "о", "л"})
    void responseProcessing_whenHIT(String answer) {
        AnswerResult res = AnswerResult.HIT;

        assertEquals(res, letterGuessed.responseProcessing(session, answer));
    }

    @ParameterizedTest
    @DisplayName("Enum: NOT_HIT")
    @ValueSource(strings = {"й", "ц", "ч", "а"})
    void responseProcessing_whenNOT_HIT(String answer) {
        AnswerResult res = AnswerResult.NOT_HIT;

        assertEquals(res, letterGuessed.responseProcessing(session, answer));
    }
}

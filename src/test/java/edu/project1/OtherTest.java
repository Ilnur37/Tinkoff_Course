package edu.project1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.project1.session.Session;
import edu.project1.session.SessionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtherTest {
    @Test
    @DisplayName("Вывод строки с * вместо букв")
    void responseProcessing_whenHit() {
        Console console = new Console();
        String needRes = "с*о*";
        Set<Character> differentLettersInAnswer1 = new HashSet<>(List.of('с', 'т', 'о', 'л'));
        Session session = new Session(
            "стол",
            5,
            differentLettersInAnswer1,
            new ArrayList<>(),
            0
        );
        SessionManager.guessResult(session, "с");
        SessionManager.guessResult(session, "о");

        assertEquals(needRes, console.getMaskedWord(session));
    }
}

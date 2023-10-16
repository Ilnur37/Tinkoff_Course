package edu.project1.difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class MiddleLevel implements DifficultyLevel {
    private static final int MAX_ATTEMPTS = 8;

    @Override
    public @NotNull String randomWord() {
        List<String> dictionary = new ArrayList<>(Arrays.asList(
            "наушники", "компьютер", "частота", "интерфейс", "наследник",
            "император", "облигации", "насекомое", "президент"
        ));
        int idx = (int) (Math.random() * dictionary.size());
        return dictionary.get(idx);
    }

    @Override
    public int maxAttempt() {
        return MAX_ATTEMPTS;
    }
}

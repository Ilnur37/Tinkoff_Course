package edu.project1.difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class EasyLevel implements DifficultyLevel {
    private static final int MAX_ATTEMPTS = 6;


    @Override
    public @NotNull String randomWord() {
        List<String> dictionary = new ArrayList<>(Arrays.asList(
            "стол", "стул", "вода", "носки", "кино", "лист", "окно",
            "клей", "кот", "собака", "шкаф", "вещь", "мама", "школа", "сон"
        ));
        int idx = (int) (Math.random() * dictionary.size());
        return dictionary.get(idx);
    }

    @Override
    public int maxAttempt() {
        return MAX_ATTEMPTS;
    }
}

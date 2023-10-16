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
            "в", "аа", "у"
        ));
        int firstIdx = (int) (Math.random() * dictionary.size());
        int idx = firstIdx;
        while (dictionary.get(idx).length() < MIN_LENGTH) {
            idx = (idx + 1) % dictionary.size();
            if (idx == firstIdx) {
                return "-1";
            }
        }
        return dictionary.get(idx);
    }

    @Override
    public int maxAttempt() {
        return MAX_ATTEMPTS;
    }
}

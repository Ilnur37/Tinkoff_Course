package edu.project1.difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class HardLevel implements DifficultyLevel {
    private static final int MAX_ATTEMPTS = 9;

    @Override
    public @NotNull String randomWord() {
        List<String> dictionary = new ArrayList<>(Arrays.asList(
            "полиморфизм", "инкапсуляция", "антропогенный", "макроэкономика",
            "гуманитарный", "паралелепипед", "тиргонометрия", "синусоинда", "биссектрисса"
        ));
        int idx = (int) (Math.random() * dictionary.size());
        return dictionary.get(idx);
    }

    @Override
    public int maxAttempt() {
        return MAX_ATTEMPTS;
    }
}

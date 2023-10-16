package edu.project1.difficulty;

import org.jetbrains.annotations.NotNull;

public interface DifficultyLevel {
    int MIN_LENGTH = 3;

    int maxAttempt();

    @NotNull String randomWord();
}

package edu.project1.difficulty;

import org.jetbrains.annotations.NotNull;

public interface DifficultyLevel {

    int maxAttempt();

    @NotNull String randomWord();
}

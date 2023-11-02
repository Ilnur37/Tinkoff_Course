package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Null")
    void task7() {
        Task7.TREE_MAP.put(null, "test");
        assertThat(Task7.TREE_MAP.containsKey(null)).isTrue();
    }
}

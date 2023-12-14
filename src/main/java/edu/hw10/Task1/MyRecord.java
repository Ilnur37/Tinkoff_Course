package edu.hw10.Task1;

import edu.hw10.Task1.annotation.Max;
import edu.hw10.Task1.annotation.Min;
import edu.hw10.Task1.annotation.NotNull;

public record MyRecord(@Min(1) @Max(3) int num, @NotNull String string) {
}

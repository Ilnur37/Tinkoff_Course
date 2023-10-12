package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Корректные данные")
    void fixString() {
        String str = "hTsii  s aimex dpus rtni.g";

        String res = Task4.fixString(str);

        assertThat(res).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Строка null")
    void fixString_shouldNullPointerException() {
        String str = null;

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Task4.fixString(str);
        }, "String is not null");

        Assertions.assertEquals("String can not be null!", thrown.getMessage());
    }

    @Test
    @DisplayName("Нечетное количество символов")
    void fixString_whenOddNumberOfCharacters() {
        String str = "21436 7";

        String res = Task4.fixString(str);

        assertThat(res).isEqualTo("1234 67");
    }

    @Test
    @DisplayName("Четное количество символов")
    void fixString_whenEvenNumberOfCharacters() {
        String str = "21436 87";

        String res = Task4.fixString(str);

        assertThat(res).isEqualTo("1234 678");
    }

}

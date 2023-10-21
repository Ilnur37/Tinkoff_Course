package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Корректные данные 1")
    void atbashCipher1() {
        String str = "Hello world!";

        String result = Task1.atbashCipher(str);

        assertThat(result).isEqualTo("Svool dliow!");
    }

    @Test
    @DisplayName("Корректные данные 2")
    void atbashCipher2() {
        String str =
            "Any fool can write code that a computer can understand. Good programmers write" +
                " code that humans can understand. ― Martin Fowler";

        String result = Task1.atbashCipher(str);

        assertThat(result).isEqualTo(
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
                "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }

    @Test
    @DisplayName("Пустая строка")
    void atbashCipher_whenStrLenIs0() {
        String str = "";

        String result = Task1.atbashCipher(str);

        assertThat(result).isEqualTo("");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null строка")
    void atbashCipher_whenStrIsNull(String str) {

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Task1.atbashCipher(str);
        }, "String is not null");

        Assertions.assertEquals("String is can not be null!", thrown.getMessage());
    }
}

package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Корректные данные 1")
    void clusteringBrackets1() {
        String str = "((()))(())()()(()())";

        List<String> trueRes = new ArrayList<>(List.of("((()))", "(())", "()", "()", "(()())"));

        assertThat(Task2.clusteringBrackets(str)).isEqualTo(trueRes);
    }

    @Test
    @DisplayName("Корректные данные 2")
    void clusteringBrackets2() {
        String str = "()()()";

        List<String> trueRes = new ArrayList<>(List.of("()", "()", "()"));

        assertThat(Task2.clusteringBrackets(str)).isEqualTo(trueRes);
    }

    @Test
    @DisplayName("Корректные данные 3")
    void clusteringBrackets3() {
        String str = "((())())(()(()()))";

        List<String> trueRes = new ArrayList<>(List.of("((())())", "(()(()()))"));

        assertThat(Task2.clusteringBrackets(str)).isEqualTo(trueRes);
    }

    @Test
    @DisplayName("Пустая строка")
    void clusteringBrackets_whenStrIsEmpty() {
        String str = "";

        List<String> trueRes = new ArrayList<>(List.of(""));

        assertThat(Task2.clusteringBrackets(str)).isEqualTo(trueRes);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null строка")
    void clusteringBrackets_whenStrIsNull(String str) {

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Task2.clusteringBrackets(str);
        }, "String is not null");

        Assertions.assertEquals("String is can not be null!", thrown.getMessage());
    }

    @Test
    @DisplayName("Нечетная длинна строки")
    void clusteringBrackets_whenOddLine() {
        String str = "((())";

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task2.clusteringBrackets(str);
        }, "String is correct");

        Assertions.assertEquals("Invalid length of string!", thrown.getMessage());
    }

    @Test
    @DisplayName("Строка содержит некорректный символ")
    void clusteringBrackets_whenInvalidCharacterInStr() {
        String str = "()()()11";

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task2.clusteringBrackets(str);
        }, "String is correct");

        Assertions.assertEquals("String extends invalid character!", thrown.getMessage());
    }

    @Test
    @DisplayName("'(' столько же сколько ')'")
    void clusteringBrackets_checkExceptionOfCountBrackets() {
        String str = "((()))";

        List<String> trueRes = new ArrayList<>(List.of("((()))"));

        assertThat(Task2.clusteringBrackets(str)).isEqualTo(trueRes);
    }

    @Test
    @DisplayName("'(' больше чем ')'")
    void clusteringBrackets_shouldExceptionOfCountBrackets() {
        String str = "()((((()))";

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task2.clusteringBrackets(str);
        }, "Count brackets correct");

        Assertions.assertEquals("'(' more than ')'!", thrown.getMessage());
    }
}

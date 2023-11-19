package edu.project3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainTest {
    private final static String ILLEGAL_ARGUMENT_EXCEPTION = "Invalid arguments were passed when running the program!";

    @Test
    @DisplayName("Нет параметров")
    void test_whenNoParameter() {
        String[] arg = new String[0];
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RunLogAnalyzer.main(arg);
        }, "Main is valid");

        Assertions.assertEquals(ILLEGAL_ARGUMENT_EXCEPTION, thrown.getMessage());
    }

    @Test
    @DisplayName("Много параметров")
    void test_whenManyParameter() {
        String[] arg = new String[10];
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RunLogAnalyzer.main(arg);
        }, "Main is valid");

        Assertions.assertEquals(ILLEGAL_ARGUMENT_EXCEPTION, thrown.getMessage());
    }

    @Test
    @DisplayName("Нечетное число параметров")
    void test_whenOddNumberOfParameters() {
        String[] arg = new String[5];
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RunLogAnalyzer.main(arg);
        }, "Main is valid");

        Assertions.assertEquals(ILLEGAL_ARGUMENT_EXCEPTION, thrown.getMessage());
    }

    @Test
    @DisplayName("Неправильно записан --path")
    void test_whenWrong_path() {
        String[] arg = new String[2];
        arg[0] = "--pathh";
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RunLogAnalyzer.main(arg);
        }, "Main is valid");

        Assertions.assertEquals(ILLEGAL_ARGUMENT_EXCEPTION, thrown.getMessage());
    }

    @Test
    @DisplayName("Аргументы дублируются")
    void test_whenReEnteringParameter() {
        String[] arg = new String[6];
        arg[0] = "--path";
        arg[1] = "qwqwqw";
        arg[2] = "--from";
        arg[3] = "2002-12-12";
        arg[4] = "--from";
        arg[5] = "2002-12-12";
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RunLogAnalyzer.main(arg);
        }, "Main is valid");

        Assertions.assertEquals(ILLEGAL_ARGUMENT_EXCEPTION, thrown.getMessage());
    }
    @Test
    @DisplayName("Передается несуществующий параметр")
    void test_whenWrongParameter() {
        String[] arg = new String[6];
        arg[0] = "--path";
        arg[1] = "qwqwqw";
        arg[2] = "--froedm";
        arg[3] = "2002-12-12";
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RunLogAnalyzer.main(arg);
        }, "Main is valid");

        Assertions.assertEquals(ILLEGAL_ARGUMENT_EXCEPTION, thrown.getMessage());
    }
}

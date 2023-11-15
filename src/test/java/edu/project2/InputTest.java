package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputTest {
    @Test
    @DisplayName("Валидные значенния размера лабиринта")
    void enterSize_valid() {
        Assertions.assertAll(
            () -> Assertions.assertFalse(AppRunner.isInvalidSize(1)),
            () -> Assertions.assertFalse(AppRunner.isInvalidSize(5)),
            () -> Assertions.assertFalse(AppRunner.isInvalidSize(15)),
            () -> Assertions.assertFalse(AppRunner.isInvalidSize(30))
        );
    }

    @Test
    @DisplayName("Невалидные значенния размера лабиринта")
    void enterSize_invalid() {
        Assertions.assertAll(
            () -> Assertions.assertTrue(AppRunner.isInvalidSize(-1)),
            () -> Assertions.assertTrue(AppRunner.isInvalidSize(0)),
            () -> Assertions.assertTrue(AppRunner.isInvalidSize(31)),
            () -> Assertions.assertTrue(AppRunner.isInvalidSize(-100)),
            () -> Assertions.assertTrue(AppRunner.isInvalidSize(100))
        );
    }

    @Test
    @DisplayName("Валидные значенния координаты старта/финиша")
    void enterPoint_valid() {
        int border = 20;
        Assertions.assertAll(
            () -> Assertions.assertFalse(AppRunner.isInvalidPoint(1, border)),
            () -> Assertions.assertFalse(AppRunner.isInvalidPoint(5, border)),
            () -> Assertions.assertFalse(AppRunner.isInvalidPoint(10, border)),
            () -> Assertions.assertFalse(AppRunner.isInvalidPoint(20, border))
        );
    }

    @Test
    @DisplayName("Невалидные значенния координаты старта/финиша")
    void enterPoint_invalid() {
        int border = 20;
        Assertions.assertAll(
            () -> Assertions.assertTrue(AppRunner.isInvalidPoint(-1, border)),
            () -> Assertions.assertTrue(AppRunner.isInvalidPoint(0, border)),
            () -> Assertions.assertTrue(AppRunner.isInvalidPoint(21, border))
        );
    }

    @Test
    @DisplayName("Валидные значенния типа решения")
    void enterTypeSolver_valid() {
        Assertions.assertAll(
            () -> Assertions.assertFalse(AppRunner.isInvalidTypeSolver(1)),
            () -> Assertions.assertFalse(AppRunner.isInvalidTypeSolver(2)),
            () -> Assertions.assertFalse(AppRunner.isInvalidTypeSolver(3))
        );
    }

    @Test
    @DisplayName("Невалидные значенния типа решения")
    void enterTypeSolver_invalid() {
        Assertions.assertAll(
            () -> Assertions.assertTrue(AppRunner.isInvalidTypeSolver(-1)),
            () -> Assertions.assertTrue(AppRunner.isInvalidTypeSolver(0)),
            () -> Assertions.assertTrue(AppRunner.isInvalidTypeSolver(4)),
            () -> Assertions.assertTrue(AppRunner.isInvalidTypeSolver(10))
        );
    }
}

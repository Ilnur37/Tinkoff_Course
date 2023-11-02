package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    @DisplayName("Корректные данные(String)")
    void freqDict1() {
        ArrayList list = new ArrayList<>(List.of("a", "bb", "a", "bb", "this", "and", "that", "and"));
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 2);
        map.put("bb", 2);
        map.put("this", 1);
        map.put("and", 2);
        map.put("that", 1);

        Assertions.assertEquals(Task3.freqDict(list), map);
    }

    @Test
    @DisplayName("Корректные данные(Integer)")
    void freqDict2() {
        ArrayList list = new ArrayList<>(List.of(1, 2, 3, 4, 2, 3, 4, 3, 4, 4, 4));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 5);

        Assertions.assertEquals(Task3.freqDict(list), map);
    }

    @Test
    @DisplayName("Пустой список")
    void freqDict_henListIsEmpty() {
        ArrayList list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        Assertions.assertEquals(Task3.freqDict(list), map);
    }

    @Test
    @DisplayName("Null аргумент")
    void freqDict_whenNullPointerException() {
        ArrayList list = null;

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Task3.freqDict(list);
        }, "List of objects is not null");

        Assertions.assertEquals("The list of objects is empty!", thrown.getMessage());
    }

    @Test
    @DisplayName("Список int и String")
    void freqDict_whenNotSameType1() {
        ArrayList list = new ArrayList(List.of(1, 2, 3, "2"));

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task3.freqDict(list);
        }, "Objects of the same type!");

        Assertions.assertEquals("Objects are not of the same type!", thrown.getMessage());
    }

    @Test
    @DisplayName("Список int и double")
    void freqDict_whenNotSameType2() {
        ArrayList list = new ArrayList(List.of(1.2, 2, 3, 4));

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task3.freqDict(list);
        }, "Objects of the same type!");

        Assertions.assertEquals("Objects are not of the same type!", thrown.getMessage());
    }

}

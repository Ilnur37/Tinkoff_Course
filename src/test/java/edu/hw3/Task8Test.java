package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task8Test {
    @Test
    @DisplayName("List int")
    void backwardIterator_ListInt() {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(list);

        List<Integer> result = new ArrayList<>();

        while (backwardIterator.hasNext()) {
            result.add(backwardIterator.next());
        }

        Collections.reverse(list);

        Assertions.assertEquals(result, list);
    }

    @Test
    @DisplayName("List String")
    void backwardIterator_ListString() {
        List<String> list = new ArrayList<>(List.of("aa", "bb", "cc", "dd", "ee"));
        BackwardIterator<String> backwardIterator = new BackwardIterator<>(list);

        List<String> result = new ArrayList<>();

        while (backwardIterator.hasNext()) {
            result.add(backwardIterator.next());
        }

        Collections.reverse(list);

        Assertions.assertEquals(result, list);
    }

    @Test
    @DisplayName("Set")
    void backwardIterator_Set() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(set);

        List<Integer> result = new ArrayList<>();

        while (backwardIterator.hasNext()) {
            result.add(backwardIterator.next());
        }

        List<Integer> list = new ArrayList<>(set.stream().toList());

        Collections.reverse(list);

        Assertions.assertEquals(result, list);
    }
}

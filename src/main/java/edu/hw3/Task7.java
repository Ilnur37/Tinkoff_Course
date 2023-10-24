package edu.hw3;

import java.util.Comparator;
import java.util.TreeMap;

public class Task7 {
    public static final TreeMap<String, String> TREE_MAP =
        new TreeMap<>(Comparator.nullsLast(Comparator.naturalOrder()));

    private Task7() {

    }
}

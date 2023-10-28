package edu.project1.difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dictionary {
    private final List<List<String>> dictionary = new ArrayList<>();
    private final int[] maxAttempts = {6, 10, 12};

    {
        List<String> dictionary1 = new ArrayList<>(Arrays.asList(
            "в", "аа", "у"
        ));

        List<String> dictionary2 = new ArrayList<>(Arrays.asList(
            "наушники", "компьютер", "частота", "интерфейс", "наследник",
            "император", "облигации", "насекомое", "президент"
        ));

        List<String> dictionary3 = new ArrayList<>(Arrays.asList(
            "полиморфизм", "инкапсуляция", "антропогенный", "макроэкономика",
            "гуманитарный", "паралелепипед", "тиргонометрия", "синусоинда", "биссектрисса"
        ));

        dictionary.add(dictionary1);
        dictionary.add(dictionary2);
        dictionary.add(dictionary3);
    }

    int getMaxDifficulty() {
        return dictionary.size();
    }

    List<String> getListOfWords(int level) {
        return dictionary.get(level);
    }

    int getMaxAttempts(int level) {
        return maxAttempts[level];
    }
}

package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task3 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task3() {

    }

    public static <T> Map<T, Integer> freqDict(ArrayList<T> list) {
        if (list == null) {
            throw new NullPointerException("The list of objects is empty!");
        }
        LOGGER.info("List: " + list);
        int length = list.size();
        Map<T, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (i < length - 1) {
                if (list.get(i).getClass() != list.get(i + 1).getClass()) {
                    throw new IllegalArgumentException("Objects are not of the same type!");
                }
            }

            map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
        }

        LOGGER.info("Map: " + map);
        return map;
    }
}

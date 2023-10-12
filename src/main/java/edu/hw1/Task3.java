package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task3 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task3() {

    }

    private static int findMaxElement(int[] arr) {
        int max = arr[0];

        for (int num : arr) {
            max = Math.max(max, num);
        }
        return max;
    }

    private static int findMinElement(int[] arr) {
        int min = arr[0];

        for (int num : arr) {
            min = Math.min(min, num);
        }
        return min;
    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        LOGGER.info("First array[]: " + Arrays.toString(arr1));
        LOGGER.info("Second array[]: " + Arrays.toString(arr2));
        if (arr2.length < 2) {
            throw new IllegalArgumentException("The length of the second array must be greater than 2!");
        }
        if (arr1.length == 0) {
            throw new IllegalArgumentException("The length of the first array must be greater than 0!");
        }

        int max1 = findMaxElement(arr1);
        int min1 = findMinElement(arr1);
        int max2 = findMaxElement(arr2);
        int min2 = findMinElement(arr2);

        return min1 > min2 && max1 < max2;
    }

}

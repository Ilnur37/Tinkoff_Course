package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task6 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int KAPREKAR_CONST = 6174;
    private final static int LEN_OF_NUMBER = 4;
    private final static int PLACE_NUMBER = 10;

    private Task6() {

    }

    private static void kIsValid(int k) {
        final int K_MIN = 1001;
        final int K_MAX = 9998;
        final int[] forbiddenNumbers = new int[] {1111, 2222, 3333, 4444, 5555, 6666, 7777, 8888, 9999};
        for (int fn : forbiddenNumbers) {
            if (k == fn) {
                throw new RuntimeException("The number must not consist of 4 identical numbers!");
            }
        }
        if (k > K_MAX || k < K_MIN) {
            throw new RuntimeException("The number k must be in the range [1001, 9999]!");
        }
    }

    private static int findKaprekarsConstant(int k) {
        int num = k;
        int[] arrOfNum = new int[LEN_OF_NUMBER];
        int max = 0;
        int min = 0;

        for (int i = LEN_OF_NUMBER - 1; i > -1; i--) {
            arrOfNum[i] = num % PLACE_NUMBER;
            num /= PLACE_NUMBER;
        }
        Arrays.sort(arrOfNum);
        for (int n : arrOfNum) {
            min *= PLACE_NUMBER;
            min += n;
        }
        for (int i = arrOfNum.length - 1; i > -1; i--) {
            max *= PLACE_NUMBER;
            max += arrOfNum[i];
        }
        if (max - min == KAPREKAR_CONST) {
            return 1;
        }
        return findKaprekarsConstant(max - min) + 1;

    }

    public static int countK(int k) {
        LOGGER.info("Number is: " + k);
        kIsValid(k);
        return findKaprekarsConstant(k);
    }

}

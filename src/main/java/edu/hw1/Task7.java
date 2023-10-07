package edu.hw1;

import java.util.Stack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task7 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task7() {

    }

    private static void validateNum(int n) {
        LOGGER.info("Number is: " + n);
        if (n <= 0) {
            throw new RuntimeException("The number must be greater than zero!");
        }
    }

    public static int rotateLeft(int n, int shift) {
        validateNum(n);
        if (shift < 0) {
            return rotateRight(n, Math.abs(shift));
        }
        int result = 0;
        int num = n;
        int idx;
        int degree;
        Stack<Integer> st = new Stack<>();

        while (num > 0) {
            st.push(num % 2);
            num /= 2;
        }
        int length = st.size();
        idx = length;

        int[] binaryShift = new int[length];
        while (!st.isEmpty()) {
            int temp = st.pop();
            binaryShift[(idx - shift) % length] = temp;
            ++idx;
        }

        degree = length - 1;
        for (int b : binaryShift) {
            result += Math.pow(2, degree) * b;
            --degree;
        }

        return result;
    }

    public static int rotateRight(int n, int shift) {
        validateNum(n);
        if (shift < 0) {
            return rotateLeft(n, Math.abs(shift));
        }
        int result = 0;
        int num = n;
        int idx;
        int degree;
        Stack<Integer> st = new Stack<>();

        while (num > 0) {
            st.push(num % 2);
            num /= 2;
        }
        int length = st.size();
        idx = 0;

        int[] binaryShift = new int[length];
        while (!st.isEmpty()) {
            int temp = st.pop();
            binaryShift[(idx + shift) % length] = temp;
            ++idx;
        }

        degree = length - 1;
        for (int b : binaryShift) {
            result += Math.pow(2, degree) * b;
            --degree;
        }

        return result;
    }

}

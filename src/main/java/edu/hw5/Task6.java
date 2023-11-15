package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    private Task6() {

    }

    public static boolean isSubsequence(String subsequence, String sequence) {
        if (subsequence == null || sequence == null) {
            throw new IllegalArgumentException("Parameters of function can not be null!");
        }
        String regex = subsequence.replace("", ".*");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sequence);
        return matcher.find();
    }
}

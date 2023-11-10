package edu.hw5;

public class Task4 {
    private static final String REGEX = ".*[~!@#$%^&*|].*";

    private Task4() {

    }

    public static boolean matchPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can not be null!");
        }
        return password.matches(REGEX);
    }
}

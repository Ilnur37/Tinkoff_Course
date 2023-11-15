package edu.hw5;


public class Task5 {
    private static final String LETTERS_USED = "[АВЕКМНОРСТУХ]";
    private static final String REGEX = "^" + LETTERS_USED + "[0-9]{3}" + LETTERS_USED + "{2}\\d{2,3}$";
    private static final int LENGTH_OF_REGION = 3;

    private Task5() {

    }

    public static boolean matchNumber(String number) {
        if (number == null) {
            throw new IllegalArgumentException("Number can not be null!");
        }
        if (!number.matches(REGEX)) {
            return false;
        }
        String region = number.substring(number.length() - LENGTH_OF_REGION, number.length());
        if (region.charAt(0) < '0' || region.charAt(0) > '9') {
            region = region.substring(1);
        }
        return region.charAt(0) != '0';
    }
}

package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MethodName")
public class Task7And8 {
    private Task7And8() {

    }

    private static void validationStr(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String can not be null!");
        }
        if (str.length() == 0) {
            throw new IllegalArgumentException("String can not be empty!");
        }
    }

    /**
     * Содержит не менее 3 символов, причем третий символ равен 0
     *
     * @param str строка
     * @return правда или ложб
     */
    public static boolean task7_1(String str) {
        validationStr(str);
        String regex = "^[01][01]0[01]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * Начинается и заканчивается одним и тем же символом
     *
     * @param str строка
     * @return правда или ложб
     */
    public static boolean task7_2(String str) {
        validationStr(str);
        char start = str.charAt(0);
        String regex = "^[" + start + "][01]*[" + start + "]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * Длина не менее 1 и не более 3
     *
     * @param str строка
     * @return правда или ложб
     */
    public static boolean task7_3(String str) {
        validationStr(str);
        String regex = "^[01]{1,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * Строки нечетной длины
     *
     * @param str строка
     * @return правда или ложб
     */
    public static boolean task8_1(String str) {
        validationStr(str);
        String regex = "^[01]([01]{2})*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * Строка начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
     *
     * @param str строка
     * @return правда или ложб
     */
    public static boolean task8_2(String str) {
        validationStr(str);
        String regex1 = "^0([01]{2})*$";
        String regex2 = "^1[01]([01]{2})*$";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(str);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(str);
        return matcher1.find() || matcher2.find();
    }

    /**
     * Количество 0 кратно 3
     *
     * @param str строка
     * @return правда или ложб
     */
    public static boolean task8_3(String str) {
        validationStr(str);
        String regex = "^(1*01*01*01*)+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * Любая строка, кроме 11 или 111
     *
     * @param str строка
     * @return правда или ложб
     */
    public static boolean task8_4(String str) {
        validationStr(str);
        String regex1 = "^11$";
        String regex2 = "^111$";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(str);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(str);
        return !(matcher1.find() || matcher2.find());
    }

    /**
     * Каждый нечетный символ равен 1
     *
     * @param str строка
     * @return правда или ложб
     */
    public static boolean task8_5(String str) {
        validationStr(str);
        String regex = "^([01]1)+0?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * Содержит не менее двух 0 и не более одной 1
     *
     * @param str строка
     * @return правда или ложб
     */
    public static boolean task8_6(String str) {
        validationStr(str);
        String regex = "^([01]*11[01]*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return !matcher.find();
    }

    /**
     * Нет последовательных 1
     *
     * @param str строка
     * @return правда или ложб
     */
    public static boolean task8_7(String str) {
        validationStr(str);
        String regex = "^([10]*1{2,}[10]*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return !matcher.find();
    }
}

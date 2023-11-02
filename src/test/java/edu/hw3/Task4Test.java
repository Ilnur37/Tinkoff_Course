package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("Корректные данные (<10)")
    void convertToRoman1() {
        String trueRes = "IX";
        int i = 9;
        Assertions.assertEquals(trueRes,Task4.convertToRoman(i));
    }

    @Test
    @DisplayName("Корректные данные (<40)")
    void convertToRoman2() {
        String trueRes = "XXXVI";
        int i = 36;
        Assertions.assertEquals(trueRes,Task4.convertToRoman(i));
    }

    @Test
    @DisplayName("Корректные данные (<100)")
    void convertToRoman3() {
        String trueRes = "LXXXIV";
        int i = 84;
        Assertions.assertEquals(trueRes,Task4.convertToRoman(i));
    }

    @Test
    @DisplayName("Корректные данные (<400)")
    void convertToRoman4() {
        String trueRes = "CCCXLVIII";
        int i = 348;
        Assertions.assertEquals(trueRes,Task4.convertToRoman(i));
    }

    @Test
    @DisplayName("Корректные данные (<900)")
    void convertToRoman5() {
        String trueRes = "DCCCXXIII";
        int i = 823;
        Assertions.assertEquals(trueRes,Task4.convertToRoman(i));
    }

    @Test
    @DisplayName("Корректные данные (<1000)")
    void convertToRoman6() {
        String trueRes = "CMXLVII";
        int i = 947;
        Assertions.assertEquals(trueRes,Task4.convertToRoman(i));
    }

    @Test
    @DisplayName("Число >3999")
    void freqDict_whenNotSameType1() {
        int i = 4000;
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task4.convertToRoman(i);
        }, "Number less than 4000!");

        Assertions.assertEquals("The number must be less than 4000!", thrown.getMessage());
    }
}

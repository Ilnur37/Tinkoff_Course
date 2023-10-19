package edu.hw2;

import edu.hw2.Task1.Addition;
import edu.hw2.Task1.Constant;
import edu.hw2.Task1.Exponent;
import edu.hw2.Task1.Multiplication;
import edu.hw2.Task1.Negate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Тест из задания")
    void expr() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, two);
        var res = new Addition(exp, new Constant(1));

        System.out.println(res + " = " + res.evaluate());
        assertThat(res.evaluate()).isEqualTo(37);
    }

    @Test
    @DisplayName("В конструктор Negate передается отрицательное число")
    void expr_whenInNegateConstructorNegateVal() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(-1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);

        System.out.println(mult + " = " + mult.evaluate());
        assertThat(mult.evaluate()).isEqualTo(6);
    }

    @Test
    @DisplayName("В конструктор Negate передается умножение")
    void expr_whenInNegateConstructorMultiplication() {
        var two = new Constant(2);
        var four = new Constant(4);
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, two);
        var neg = new Negate(mult);

        System.out.println(neg + " = " + neg.evaluate());
        assertThat(neg.evaluate()).isEqualTo(-12);
    }

    @Test
    @DisplayName("Возведение отрицательного числа в дробную степень")
    void expr_whenIllegalArgumentException() {
        var two = new Constant(-2);
        var four = new Constant(1.2);
        var exp = new Exponent(two, four);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class,
            exp::evaluate, "No Exception"
        );

        Assertions.assertEquals("Raising a negative number to a fractional power is impossible!",
            thrown.getMessage());
    }
}

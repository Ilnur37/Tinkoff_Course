package edu.hw11;

import edu.hw11.Task2.ArithmeticUtils;
import edu.hw11.Task2.MyInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    void task2() throws InstantiationException, IllegalAccessException {
        Class<?> modifiedClass = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(MyInterceptor.class))
            .make()
            .load(getClass()
                .getClassLoader())
            .getLoaded();

        ArithmeticUtils instance = (ArithmeticUtils) modifiedClass.newInstance();
        int n1 = 3;
        int n2 = 4;
        int result = instance.sum(n1, n2);
        Assertions.assertEquals(3 * 4, result);
    }
}

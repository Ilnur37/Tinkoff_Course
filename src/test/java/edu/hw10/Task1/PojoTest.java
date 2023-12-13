package edu.hw10.Task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PojoTest {
    @Test
    void oneIntConsrt()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        Constructor<?> constructor = MyClass.class.getDeclaredConstructor(int.class);
        for (int i = 0; i < 1000; i++) {
            MyClass myPOJO = (MyClass) constructor.newInstance(rog.generatePOJOParameters(constructor.getParameters()));
            Assertions.assertTrue(myPOJO.getNum1() < 2 && myPOJO.getNum1() >= 1);
        }
    }

    @Test
    void oneStrConsrt()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        Constructor<?> constructor = MyClass.class.getDeclaredConstructor(String.class);
        for (int i = 0; i < 1000; i++) {
            MyClass myPOJO = (MyClass) constructor.newInstance(rog.generatePOJOParameters(constructor.getParameters()));
            Assertions.assertNull(myPOJO.getName());
        }
    }

    @Test
    void twoIntConsrt()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        Constructor<?> constructor = MyClass.class.getDeclaredConstructor(int.class, double.class);
        for (int i = 0; i < 1000; i++) {
            MyClass myPOJO = (MyClass) constructor.newInstance(rog.generatePOJOParameters(constructor.getParameters()));
            Assertions.assertAll(
                () -> Assertions.assertTrue(myPOJO.getNum1() < 2 && myPOJO.getNum1() >= 1),
                () -> Assertions.assertTrue(myPOJO.getNum2() < 1)
            );
        }
    }

    @Test
    void twoStrConsrt()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        Constructor<?> constructor = MyClass.class.getDeclaredConstructor(String.class, String.class);
        for (int i = 0; i < 1000; i++) {
            MyClass myPOJO = (MyClass) constructor.newInstance(rog.generatePOJOParameters(constructor.getParameters()));
            Assertions.assertAll(
                () -> Assertions.assertNotNull(myPOJO.getName()),
                () -> Assertions.assertNull(myPOJO.getSecondName())
            );
        }
    }

    @Test
    void AllArgConsrt()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        Constructor<?> constructor =
            MyClass.class.getDeclaredConstructor(int.class, double.class, String.class, String.class);
        for (int i = 0; i < 1000; i++) {
            MyClass myPOJO = (MyClass) constructor.newInstance(rog.generatePOJOParameters(constructor.getParameters()));
            Assertions.assertAll(
                () -> Assertions.assertTrue(myPOJO.getNum2() >= 1 && myPOJO.getNum2() < 2),
                () -> Assertions.assertNotNull(myPOJO.getName()),
                () -> Assertions.assertNull(myPOJO.getSecondName())
            );
        }
    }

    @Test
    void methodCreateWithTwoParam() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        Method method = MyClass.class.getMethod("create", double.class, String.class);
        for (int i = 0; i < 1000; i++) {
            MyClass myPOJO = new MyClass();
            myPOJO = (MyClass) method.invoke(myPOJO, rog.generatePOJOParameters(method.getParameters()));
            Assertions.assertTrue(myPOJO.getNum2() >= 1 && myPOJO.getNum2() < 2);
            Assertions.assertNotNull(myPOJO.getName());
        }
    }

    @Test
    void methodCreateWithThreeParam() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        Method method = MyClass.class.getMethod("create", int.class, double.class, String.class);
        for (int i = 0; i < 1000; i++) {
            MyClass myPOJO = new MyClass();
            myPOJO = (MyClass) method.invoke(myPOJO, rog.generatePOJOParameters(method.getParameters()));
            Assertions.assertTrue(myPOJO.getNum1() >= 1 && myPOJO.getNum1() < 2);
            Assertions.assertNotNull(myPOJO.getName());
        }
    }

}

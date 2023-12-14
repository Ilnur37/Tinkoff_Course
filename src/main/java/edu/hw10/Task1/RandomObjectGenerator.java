package edu.hw10.Task1;

import edu.hw10.Task1.annotation.MinMaxAnnotationHandler;
import edu.hw10.Task1.annotation.NotNullAnnotationHandler;
import edu.hw10.Task1.generators.Generator;
import edu.hw10.Task1.generators.NumGenerator;
import edu.hw10.Task1.generators.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;

public class RandomObjectGenerator {

    public RandomObjectGenerator() {
    }

    public <T> T nextObject(Class<T> tClass) {
        if (tClass.isRecord()) {
            return generateRandomRecord(tClass);
        } else {
            return generateRandomPojoByConstr(tClass);
        }
    }

    public <T> T nextObject(Class<T> tClass, String method) {
        return generateRandomPojoByFactory(tClass, method);
    }

    @SneakyThrows({InvocationTargetException.class, InstantiationException.class, IllegalAccessException.class})
    private <T> T generateRandomPojoByConstr(Class<T> pojoClass) {
        Constructor<?>[] constructors = pojoClass.getDeclaredConstructors();
        int idxConstr = ThreadLocalRandom.current().nextInt(0, constructors.length);
        Constructor<T> constructor = (Constructor<T>) constructors[idxConstr];
        Parameter[] params = constructor.getParameters();

        return constructor.newInstance(generatePOJOParameters(params));
    }

    @SneakyThrows({InvocationTargetException.class, IllegalAccessException.class})
    private <T> T generateRandomPojoByFactory(Class<T> pojoClass, String methodName) {
        Method[] allMethods = pojoClass.getMethods();
        List<Method> factoryMethods = new ArrayList<>();
        for (var currMethod : allMethods) {
            if (currMethod.getName().equals(methodName)) {
                factoryMethods.add(currMethod);
            }
        }
        int idxMethod = ThreadLocalRandom.current().nextInt(0, factoryMethods.size());
        Object[] parameters = generatePOJOParameters(factoryMethods.get(idxMethod).getParameters());
        MyClass myClass = new MyClass();
        return (T) factoryMethods.get(idxMethod).invoke(myClass, parameters);
    }

    public Object[] generatePOJOParameters(Parameter[] params) {
        Object[] filledParams = new Object[params.length];

        for (int i = 0; i < params.length; i++) {
            Parameter currParam = params[i];
            Generator gen;
            if (isNumber(currParam.getType())) {
                NumGenerator numGenerator = new NumGenerator();
                gen = (Generator) Proxy.newProxyInstance(
                    numGenerator.getClass().getClassLoader(),
                    new Class[] {Generator.class},
                    new MinMaxAnnotationHandler(numGenerator)
                );
            } else if (isString(currParam.getType())) {
                StringGenerator strGenerator = new StringGenerator();
                gen = (Generator) Proxy.newProxyInstance(
                    strGenerator.getClass().getClassLoader(),
                    new Class[] {Generator.class},
                    new NotNullAnnotationHandler(strGenerator)
                );
            } else {
                throw new IllegalArgumentException("This type of parameter is not yet supported!");
            }
            filledParams[i] = gen.generate(currParam);
        }

        return filledParams;
    }

    @SneakyThrows({InvocationTargetException.class, InstantiationException.class, IllegalAccessException.class})
    private <T> T generateRandomRecord(Class<T> recordClass) {
        Constructor<T> constructor = (Constructor<T>) recordClass.getDeclaredConstructors()[0];
        Parameter[] params = constructor.getParameters();
        return constructor.newInstance(generatePOJOParameters(params));

    }

    private boolean isNumber(Class<?> type) {
        return (type == int.class || type == Integer.class
            || type == double.class || type == Double.class
            || type == boolean.class || type == Boolean.class);
    }

    private boolean isString(Class<?> type) {
        return (type == String.class);
    }
}

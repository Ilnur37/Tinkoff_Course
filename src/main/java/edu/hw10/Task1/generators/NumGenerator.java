package edu.hw10.Task1.generators;

import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class NumGenerator implements Generator {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public Object generate(Parameter parameter) {
        Class<?> type = parameter.getType();
        Object result;
        if (type == int.class || type == Integer.class) {
            result = RANDOM.nextInt();
        } else if (type == double.class || type == Double.class) {
            result = RANDOM.nextDouble();
        } else if (type == boolean.class || type == Boolean.class) {
            result = RANDOM.nextBoolean();
        } else {
            throw new IllegalArgumentException("This parameter type is not supported");
        }

        return result;
    }
}

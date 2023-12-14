package edu.hw10.Task1.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class MinMaxAnnotationHandler implements InvocationHandler {
    private final Object target;
    double minValue = Double.MIN_VALUE;
    double maxValue = Double.MAX_VALUE;

    public MinMaxAnnotationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object arg = args[0];
        Parameter param = (Parameter) arg;
        boolean hasAnnotation = false;
        Min minAnnotation = param.getAnnotation(Min.class);
        Max maxAnnotation = param.getAnnotation(Max.class);
        if (minAnnotation != null) {
            hasAnnotation = true;
            minValue = minAnnotation.value();
        }
        if (maxAnnotation != null) {
            hasAnnotation = true;
            maxValue = maxAnnotation.value();
        }
        if (hasAnnotation) {
            Class<?> type = param.getType();
            return generateNum(type);
        }
        return method.invoke(target, args);
    }

    public Object generateNum(Class<?> type) {
        if (type == int.class || type == Integer.class) {
            return ThreadLocalRandom.current().nextInt((int) minValue, (int) maxValue);
        } else if (type == double.class || type == Double.class) {
            return ThreadLocalRandom.current().nextDouble(minValue, maxValue);
        } else if (type == boolean.class || type == Boolean.class) {
            return ThreadLocalRandom.current().nextBoolean();
        } else {
            throw new IllegalArgumentException("This parameter type is not supported");
        }
    }
}

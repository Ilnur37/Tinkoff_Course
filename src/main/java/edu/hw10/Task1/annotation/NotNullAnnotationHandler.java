package edu.hw10.Task1.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class NotNullAnnotationHandler implements InvocationHandler {
    private static final int MAX_STRING_LENGTH = 12;
    private final Object target;

    public NotNullAnnotationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object arg = args[0];
        Parameter param = (Parameter) arg;
        if ((param).isAnnotationPresent(NotNull.class)) {
            return generateStr();
        }
        return method.invoke(target, args);
    }

    private String generateStr() {
        int length = ThreadLocalRandom.current().nextInt(0, MAX_STRING_LENGTH);
        StringBuilder sb = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current().nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
}

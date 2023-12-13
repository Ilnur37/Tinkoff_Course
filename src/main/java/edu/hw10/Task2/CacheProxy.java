package edu.hw10.Task2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache;
    private final Path cacheDirectory;

    private CacheProxy(Object target, Path cacheDirectory) {
        this.target = target;
        this.cache = new ConcurrentHashMap<>();
        this.cacheDirectory = cacheDirectory;
    }

    public static <T> T create(T target, Class<?> interfaceClass, Path cacheDirectory) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass},
            new CacheProxy(target, cacheDirectory)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String fileName = method.getName() + Arrays.stream(method.getParameterTypes()).toList();
        String key = Arrays.stream(args).toList().toString();
        Path filePath = cacheDirectory.resolve(fileName + ".txt");

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        Object result = method.invoke(target, args);
        if (method.isAnnotationPresent(Cache.class)) {
            writeResultToFile(filePath, args, result);
        }

        cache.put(key, result);
        return result;
    }

    private void writeResultToFile(Path filePath, Object[] args, Object result) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(
            filePath,
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        )) {
            StringBuilder sb = new StringBuilder();
            for (var arg : args) {
                sb.append(arg.toString()).append(" ");
            }
            sb.append("\n").append(result.toString()).append("\n");
            writer.write(sb.toString());
        }
    }

    /*private Object readResultFromFile(Path filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(filePath))) {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}

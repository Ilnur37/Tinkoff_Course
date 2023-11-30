package edu.hw8.Task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

public class PasswordDecoderMultiThread implements PasswordDecoder {
    private final int threads;
    private final ExecutorService executorService;
    @Getter
    private final Map<String, String> decodePassByUser = new ConcurrentHashMap<>();
    private final Map<String, String> userByEncodePass = new ConcurrentHashMap<>();

    public PasswordDecoderMultiThread(int threads) {
        this.threads = threads;
        this.executorService = Executors.newFixedThreadPool(threads);
    }

    @Override
    public void encodePasswordArray(@NotNull Map<String, String> users) {
        PASSWORD_BY_USER.putAll(users);
        for (Map.Entry<String, String> user : users.entrySet()) {
            userByEncodePass.put(Arrays.toString(Encoder.encodeMD5(user.getValue())), user.getKey());
        }
    }

    @Override
    @SuppressWarnings("ReturnCount")
    @SneakyThrows({InterruptedException.class, ExecutionException.class})
    public void decodePassword(int maxLength) {
        if (userByEncodePass.isEmpty()) {
            return;
        }
        for (int wordLength = 1; wordLength <= maxLength; wordLength++) {
            List<Callable<Void>> tasks = createTasks(wordLength);
            List<Future<Void>> futures = executorService.invokeAll(tasks);

            for (var future : futures) {
                future.get();
            }
            if (userByEncodePass.isEmpty()) {
                executorService.shutdown();
                return;
            }
        }
    }

    private List<Callable<Void>> createTasks(int wordLength) {
        int totalCombinations = (int) Math.pow(DICTIONARY_POWER, wordLength);
        int partOfTotalCombinations = totalCombinations / threads;
        int[] starts = new int[threads];
        int[] ends = new int[threads];

        for (int j = 0; j < threads; j++) {
            starts[j] = partOfTotalCombinations * j;
            ends[j] = partOfTotalCombinations * (j + 1) - 1;
        }
        ends[threads - 1] = totalCombinations;

        List<Callable<Void>> tasks = new ArrayList<>();
        for (int j = 0; j < threads; j++) {
            int finalJ = j;
            tasks.add(() -> {
                searchPassword(starts[finalJ], ends[finalJ], wordLength);
                return null;
            });
        }

        return tasks;
    }

    private void searchPassword(int start, int totalCombinations, int wordLength) {
        for (int i = start; i < totalCombinations; i++) {
            StringBuilder word = new StringBuilder();
            int remainder = i;

            for (int j = 0; j < wordLength; j++) {
                int charIndex = remainder % DICTIONARY_POWER;
                word.append(getChar(charIndex));
                remainder /= DICTIONARY_POWER;
            }

            String pass = word.reverse().toString();
            String encodePass = Arrays.toString(Encoder.encodeMD5(pass));
            if (userByEncodePass.containsKey(encodePass)) {
                String user = userByEncodePass.get(encodePass);
                LOGGER.info("Найден пароль " + pass + "(MD5 {byte[]: " + encodePass + "}. Аккаунт " + user);
                userByEncodePass.remove(encodePass);
                decodePassByUser.put(user, pass);
            }
            if (userByEncodePass.isEmpty()) {
                return;
            }
        }
    }
}

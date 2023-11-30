package edu.hw8.Task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
public class PasswordDecoderSingleThread implements PasswordDecoder {
    private final Map<String, String> userByEncodePasse = new HashMap<>();
    private final Map<String, String> decodePassByUser = new HashMap<>();

    @Override
    public void encodePasswordArray(@NotNull Map<String, String> users) {
        PASSWORD_BY_USER.putAll(users);
        for (Map.Entry<String, String> user : users.entrySet()) {
            userByEncodePasse.put(Arrays.toString(Encoder.encodeMD5(user.getValue())), user.getKey());
        }
    }

    @Override
    @SuppressWarnings("ReturnCount")
    public void decodePassword(int maxLength) {
        long startTime = System.currentTimeMillis();
        if (userByEncodePasse.isEmpty()) {
            return;
        }
        for (int wordLength = 1; wordLength <= maxLength; wordLength++) {
            int totalCharacters = DICTIONARY_POWER;
            int totalCombinations = (int) Math.pow(totalCharacters, wordLength);

            for (int i = 0; i < totalCombinations; i++) {
                StringBuilder word = new StringBuilder();
                int remainder = i;

                for (int j = 0; j < wordLength; j++) {
                    int charIndex = remainder % totalCharacters;
                    word.append(getChar(charIndex));
                    remainder /= totalCharacters;
                }

                String pass = word.reverse().toString();
                String encodePass = Arrays.toString(Encoder.encodeMD5(pass));
                if (userByEncodePasse.containsKey(encodePass)) {
                    String user = userByEncodePasse.get(encodePass);
                    LOGGER.info("Найден пароль " + pass + "(MD5 {byte[]: " + encodePass + "}. Аккаунт " + user);
                    decodePassByUser.put(user, pass);
                    userByEncodePasse.remove(encodePass);
                    if (userByEncodePasse.isEmpty()) {
                        long finishTime = System.currentTimeMillis();
                        long elapsed = finishTime - startTime;
                        System.out.println("Прошло времени, мс: " + elapsed);
                        return;
                    }
                }
            }
        }
    }
}

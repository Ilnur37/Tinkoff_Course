package edu.hw8;

import edu.hw8.Task3.PasswordDecoderMultiThread;
import edu.hw8.Task3.PasswordDecoderSingleThread;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class Task3Test {
    @Test
    public void singleThread() {
        PasswordDecoderSingleThread passwordDecode= new PasswordDecoderSingleThread();
        Map<String, String> map = new HashMap<>();
        map.put("aaaa", "abc4");
        map.put("bbbb", "qwer");
        map.put("cccc", "1234");
        passwordDecode.encodePasswordArray(map);
        passwordDecode.decodePassword(6);
        Assertions.assertEquals(map, passwordDecode.getDecodePassByUser());
    }

    @Test
    public void multiThread() {
        PasswordDecoderMultiThread passwordDecode= new PasswordDecoderMultiThread(6);
        Map<String, String> map = new HashMap<>();
        map.put("aaaa", "abc4");
        map.put("bbbb", "qwer");
        map.put("cccc", "1234");
        passwordDecode.encodePasswordArray(map);
        passwordDecode.decodePassword(6);
        Assertions.assertEquals(map, passwordDecode.getDecodePassByUser());
    }
}

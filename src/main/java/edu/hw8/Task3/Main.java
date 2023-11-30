package edu.hw8.Task3;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        /*PasswordDecoderMultiThread passwordDecodeM = new PasswordDecoderMultiThread(6);
        Map<String, String> map2 = new HashMap<>();
        map2.put("eeee", "qq");
        passwordDecodeM.encodePasswordArray(map2);
        passwordDecodeM.decodePassword(6);*/

        /*PasswordDecoderMultiThread passwordDecodeM3 = new PasswordDecoderMultiThread(4);
        Map<String, String> map3 = new HashMap<>();
        map3.put("eeee", "igkl");
        passwordDecodeM3.encodePasswordArray(map3);
        passwordDecodeM3.decodePassword(6);*/

        PasswordDecoderMultiThread passwordDecodeM4 = new PasswordDecoderMultiThread(3);
        Map<String, String> map4= new HashMap<>();
        map4.put("eeee", "00");
        passwordDecodeM4.encodePasswordArray(map4);
        passwordDecodeM4.decodePassword(6);

        /*PasswordDecoderSingleThread passwordDecode = new PasswordDecoderSingleThread();
        Map<String, String> map = new HashMap<>();
        map.put("eeee", "00");
        passwordDecode.encodePasswordArray(map);
        passwordDecode.decodePassword(6);*/
    }
}

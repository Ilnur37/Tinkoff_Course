package edu.hw6;

import edu.hw6.Task1.DiskMap;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("get & containsKey")
    void diskMap1() throws IOException {
        DiskMap diskMap = new DiskMap();
        diskMap.put("aa", "aaa");
        diskMap.put("bb", "bbb");
        diskMap.put("cc", "ccc");
        Assertions.assertAll(
            () -> Assertions.assertEquals(3, diskMap.size()),
            () -> Assertions.assertEquals("aaa", diskMap.get("aa")),
            () -> Assertions.assertEquals("bbb", diskMap.get("bb")),
            () -> Assertions.assertEquals("ccc", diskMap.get("cc")),
            () -> Assertions.assertTrue(diskMap.containsKey("aa")),
            () -> Assertions.assertTrue(diskMap.containsKey("bb")),
            () -> Assertions.assertTrue(diskMap.containsKey("cc"))
        );
    }

    @Test
    @DisplayName("containsValue & containsKey")
    void diskMap2() throws IOException {
        DiskMap diskMap = new DiskMap();
        diskMap.put("aa", "aaa");
        diskMap.put("bb", "bbb");
        diskMap.put("cc", "ccc");

        diskMap.put("aa", "AAA");
        diskMap.put("bb", "BBB");
        diskMap.put("cc", "CCC");
        Assertions.assertAll(
            () -> Assertions.assertEquals(3, diskMap.size()),
            () -> Assertions.assertEquals("AAA", diskMap.get("aa")),
            () -> Assertions.assertEquals("BBB", diskMap.get("bb")),
            () -> Assertions.assertEquals("CCC", diskMap.get("cc")),

            () -> Assertions.assertTrue(diskMap.containsKey("aa")),
            () -> Assertions.assertTrue(diskMap.containsKey("bb")),
            () -> Assertions.assertTrue(diskMap.containsKey("cc")),

            () -> Assertions.assertTrue(diskMap.containsValue("AAA")),
            () -> Assertions.assertTrue(diskMap.containsValue("BBB")),
            () -> Assertions.assertTrue(diskMap.containsValue("CCC")),

            () -> Assertions.assertFalse(diskMap.containsValue("aaa")),
            () -> Assertions.assertFalse(diskMap.containsValue("bbb")),
            () -> Assertions.assertFalse(diskMap.containsValue("ccc"))
        );
    }

    @Test
    @DisplayName("remove")
    void diskMap3() throws IOException {
        DiskMap diskMap = new DiskMap();
        diskMap.put("aa", "aaa");
        diskMap.put("bb", "bbb");
        diskMap.put("cc", "ccc");

        diskMap.remove("bb");
        diskMap.remove("cc");
        Assertions.assertAll(
            () -> Assertions.assertEquals(1, diskMap.size()),
            () -> Assertions.assertEquals("aaa", diskMap.get("aa")),
            () -> Assertions.assertNull(diskMap.get("bb")),
            () -> Assertions.assertNull(diskMap.get("cc")),

            () -> Assertions.assertTrue(diskMap.containsKey("aa")),
            () -> Assertions.assertFalse(diskMap.containsKey("bb")),
            () -> Assertions.assertFalse(diskMap.containsKey("cc"))
        );
    }

    @Test
    @DisplayName("clear")
    void diskMap4() throws IOException {
        DiskMap diskMap = new DiskMap();
        diskMap.put("aa", "aaa");
        diskMap.put("bb", "bbb");
        diskMap.put("cc", "ccc");
        diskMap.clear();
        Assertions.assertAll(
            () -> Assertions.assertEquals(0, diskMap.size()),
            () -> Assertions.assertTrue(diskMap.isEmpty())
        );
    }

    @Test
    @DisplayName("keySet & values")
    void diskMap5() throws IOException {
        DiskMap diskMap = new DiskMap();
        Set<String> keys = new HashSet<>(List.of("aa", "bb", "cc"));
        Collection<String> values = new HashSet<>(List.of("aaa", "bbb", "ccc"));
        diskMap.put("aa", "aaa");
        diskMap.put("bb", "bbb");
        diskMap.put("cc", "ccc");
        Assertions.assertAll(
            () -> Assertions.assertEquals(3, diskMap.size()),
            () -> Assertions.assertEquals(keys, diskMap.keySet()),
            () -> Assertions.assertEquals(values, diskMap.values())
        );
    }

    @Test
    @DisplayName("keySet & values")
    void diskMap6() throws IOException {
        DiskMap diskMap = new DiskMap();
        Map<String, String> map = new HashMap<>();
        diskMap.put("aa", "aaa");
        diskMap.put("bb", "bbb");
        diskMap.put("cc", "ccc");
        map.put("aa", "aaa");
        map.put("bb", "bbb");
        map.put("cc", "ccc");
        Assertions.assertAll(
            () -> Assertions.assertEquals(3, diskMap.size()),
            () -> Assertions.assertEquals(map.entrySet(), diskMap.entrySet())
        );
    }
}

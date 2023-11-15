package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String FILE_WRITER_EXCEPTION = "Failed to start reading file. File not found";
    private final String path = "src/test/resources/hw6/task1/";
    private final String txt = ".txt";
    private final String separator = " : ";
    private final File directory;
    private final Set<String> nameFiles = new HashSet<>();

    public DiskMap() {
        directory = new File(path.substring(0, path.length() - 1));
        directory.mkdir();
    }

    private void saveValue(String key, String value) {
        File file = new File(path + key + txt);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(key + separator + value);
            writer.close();
            file.createNewFile();
            file.deleteOnExit();
            nameFiles.add(key);
        } catch (IOException e) {
            LOGGER.info(FILE_WRITER_EXCEPTION);
        }
    }

    private void updateValue(String key, String value) {
        File file = new File(path + key + txt);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(key + separator + value);
            writer.close();
        } catch (IOException e) {
            LOGGER.info(FILE_WRITER_EXCEPTION);
        }
    }

    private String getValueFromFile(String fileName) throws IOException {
        File file = new File(fileName + txt);
        BufferedReader reader = new BufferedReader(new FileReader(path + file));
        String line = reader.readLine();
        reader.close();
        String[] parts = line.split(separator, 2);
        return parts[1];
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.list()).length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return Arrays.asList(
                Objects.requireNonNull(directory.list())
            )
            .contains(key + txt);
    }

    @Override
    public boolean containsValue(Object value) {
        var values = new ArrayList<>();
        for (String file : nameFiles) {
            try {
                values.add(getValueFromFile(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return values.contains(value);
    }

    @Override
    public String get(Object key) {
        if (!nameFiles.contains(key)) {
            return null;
        }
        try {
            return getValueFromFile((String) key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (nameFiles.contains(key)) {
            String lastVal;
            try {
                lastVal = getValueFromFile(key);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            updateValue(key, value);
            return lastVal;
        } else {
            saveValue(key, value);
            return null;
        }
    }

    @Override
    public String remove(Object key) {
        if (nameFiles.contains((String) key)) {
            String lastVal;
            try {
                lastVal = getValueFromFile((String) key);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            nameFiles.remove(key);
            File file = new File(path + key + txt);
            file.delete();
            return lastVal;
        } else {
            return null;
        }
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {
        var keys = nameFiles.toArray();
        for (Object key : keys) {
            remove(key);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return nameFiles;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        Collection<String> values = new HashSet<>();
        var keys = nameFiles.toArray();
        for (Object key : keys) {
            try {
                values.add(getValueFromFile((String) key));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return values;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Map<String, String> map = new HashMap<>();
        for (String key : nameFiles) {
            try {
                map.put(key, getValueFromFile(key));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return map.entrySet();
    }
}

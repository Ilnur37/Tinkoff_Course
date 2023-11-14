package edu.hw6.Task3;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static DirectoryStream.Filter<Path> largerThan(long size) {
        return path -> Files.size(path) > size;
    }

    static DirectoryStream.Filter<Path> globMatches(String glob) {
        Pattern pattern = Pattern.compile(glob
            .replace(".", "\\.")
            .replace("*", ".*")
        );
        return entry -> pattern.matcher(entry.getFileName().toString()).matches();
    }

    static DirectoryStream.Filter<Path> regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return entry -> pattern.matcher(entry.getFileName().toString()).find();
    }

    @SafeVarargs static DirectoryStream.Filter<Path> and(DirectoryStream.Filter<Path>... filters) {
        return entry -> {
            for (DirectoryStream.Filter<Path> filter : filters) {
                if (!filter.accept(entry)) {
                    return false;
                }
            }
            return true;
        };
    }

    static AbstractFilter magicNumber(int... magicBytes) {
        return path -> {
            try (InputStream is = Files.newInputStream(path)) {
                byte[] headerBytes = new byte[magicBytes.length];
                int bytesRead = is.read(headerBytes);

                if (bytesRead == magicBytes.length) {
                    for (int i = 0; i < magicBytes.length; i++) {
                        if (headerBytes[i] != (byte) magicBytes[i]) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        };
    }
}

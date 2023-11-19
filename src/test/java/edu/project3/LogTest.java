package edu.project3;

import edu.project3.log.LogAnalyzer;
import edu.project3.log.LogEntry;
import edu.project3.typeOfStatistic.TypeOutputFile;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LogTest {
    @Test
    @DisplayName("Поиск файлов '*.txt'")
    void findFiles1() throws IOException {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        List<Path> files = logAnalyzer.findFiles("*.txt");
        List<Path> trueFiles = new ArrayList<>();
        trueFiles.add(Path.of("src/main/resources/project3/All Logs/Edu log2.txt"));
        trueFiles.add(Path.of("src/main/resources/project3/All Logs/Other log.txt"));
        Assertions.assertTrue(files.size() == trueFiles.size()
            && files.containsAll(trueFiles) && trueFiles.containsAll(files));
    }

    @Test
    @DisplayName("Поиск файлов '**.txt'")
    void findFiles2() throws IOException {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        List<Path> files = logAnalyzer.findFiles("**.txt");
        List<Path> trueFiles = new ArrayList<>();
        trueFiles.add(Path.of("src/main/resources/project3/All Logs/Edu log2.txt"));
        trueFiles.add(Path.of("src/main/resources/project3/All Logs/Other log.txt"));
        trueFiles.add(Path.of("src/main/resources/project3/All Logs/server1/Bank log1.txt"));
        trueFiles.add(Path.of("src/main/resources/project3/All Logs/server2/Bank log2.txt"));
        trueFiles.add(Path.of("src/main/resources/project3/All Logs/server2/Edu log1.txt"));
        Assertions.assertTrue(files.size() == trueFiles.size()
            && files.containsAll(trueFiles) && trueFiles.containsAll(files));
    }

    @Test
    @DisplayName("Поиск файлов '*/*.txt'")
    void findFiles3() throws IOException {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        List<Path> files = logAnalyzer.findFiles("*/*.txt");
        List<Path> trueFiles = new ArrayList<>();
        trueFiles.add(Path.of("src/main/resources/project3/All Logs/server1/Bank log1.txt"));
        trueFiles.add(Path.of("src/main/resources/project3/All Logs/server2/Bank log2.txt"));
        trueFiles.add(Path.of("src/main/resources/project3/All Logs/server2/Edu log1.txt"));
        Assertions.assertTrue(files.size() == trueFiles.size()
            && files.containsAll(trueFiles) && trueFiles.containsAll(files));
    }

    @Test
    @DisplayName("Создание log объектов")
    void logEntry() throws ParseException {
        List<LogEntry> logs = new ArrayList<>();
        logs.add(new LogEntry(
            "54.84.191.5 - - [20/May/2015:06:05:00 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\""));
        logs.add(new LogEntry(
            "210.66.181.30 - - [20/May/2015:11:05:36 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 85619205 \"http://logstash.net/\" \"Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36\""));

        Assertions.assertAll(
            () -> Assertions.assertEquals("54.84.191.5", logs.get(0).getIpAddress()),
            () -> Assertions.assertEquals(
                OffsetDateTime.of(2015, 5, 20, 6, 5, 0, 0, ZoneOffset.ofHours(0)),
                logs.get(0).getDateTime()
            ),
            () -> Assertions.assertEquals("GET", logs.get(0).getMethod()),
            () -> Assertions.assertEquals("/downloads/product_2", logs.get(0).getEndpoint()),
            () -> Assertions.assertEquals("HTTP/1.1", logs.get(0).getProtocol()),
            () -> Assertions.assertEquals(304, logs.get(0).getResponseCode()),
            () -> Assertions.assertEquals(0, logs.get(0).getResponseSize()),
            () -> Assertions.assertEquals("\"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"", logs.get(0).getUserAgent()),

            () -> Assertions.assertEquals("210.66.181.30", logs.get(1).getIpAddress()),
            () -> Assertions.assertEquals(
                OffsetDateTime.of(2015, 5, 20, 11, 5, 36, 0, ZoneOffset.ofHours(0)),
                logs.get(1).getDateTime()
            ),
            () -> Assertions.assertEquals("GET", logs.get(1).getMethod()),
            () -> Assertions.assertEquals("/downloads/product_1", logs.get(1).getEndpoint()),
            () -> Assertions.assertEquals("HTTP/1.1", logs.get(1).getProtocol()),
            () -> Assertions.assertEquals(200, logs.get(1).getResponseCode()),
            () -> Assertions.assertEquals(85619205, logs.get(1).getResponseSize()),
            () -> Assertions.assertEquals(
                "\"http://logstash.net/\" \"Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36\"",
                logs.get(1).getUserAgent()
            )
        );
    }

    @Test
    @DisplayName("Сканирование файла")
    void analyzeFile() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        List<Path> file = new ArrayList<>(List.of(Path.of("src/test/resources/project3/testLogs.txt")));
        logAnalyzer.analyzeFile(file, null, null, TypeOutputFile.MARKDOWN);
        Map<String, Integer> responseCodeMap = new HashMap<>();
        responseCodeMap.put("304", 4);
        responseCodeMap.put("200", 4);
        responseCodeMap.put("404", 8);
        responseCodeMap.put("403", 4);
        Map<String, Integer> endpointMap = new HashMap<>();
        endpointMap.put("/downloads/product_2", 12);
        endpointMap.put("/downloads/product_1", 8);
        Map<LocalDate, Integer> requestsPerDayMap = new HashMap<>();
        requestsPerDayMap.put(LocalDate.of(2015, 5, 20), 5);
        requestsPerDayMap.put(LocalDate.of(2015, 5, 21), 5);
        requestsPerDayMap.put(LocalDate.of(2015, 5, 22), 5);
        requestsPerDayMap.put(LocalDate.of(2015, 5, 23), 5);

        Assertions.assertAll(
            () -> Assertions.assertEquals(40, logAnalyzer.getAverageResponseSize()),
            () -> Assertions.assertEquals(20, logAnalyzer.getCountResponse()),
            () -> Assertions.assertEquals(LocalDate.of(2015, 5, 20), logAnalyzer.getFromDate()),
            () -> Assertions.assertEquals(LocalDate.of(2015, 5, 23), logAnalyzer.getToDate()),
            () -> Assertions.assertEquals(responseCodeMap, logAnalyzer.getResponseCodeMap()),
            () -> Assertions.assertEquals(endpointMap, logAnalyzer.getEndpointMap()),
            () -> Assertions.assertEquals(requestsPerDayMap, logAnalyzer.getRequestsPerDayMap())
        );
    }
}

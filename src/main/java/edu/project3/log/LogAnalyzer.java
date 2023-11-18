package edu.project3.log;

import edu.project3.typeOfStatistic.AdocStatistic;
import edu.project3.typeOfStatistic.MdStatistic;
import edu.project3.typeOfStatistic.Statistic;
import edu.project3.typeOfStatistic.TypeOutputFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LogAnalyzer {
    private long fullResponseSize = 0;
    private long averageResponseSize = 0;
    private int countResponse = 0;
    private LocalDate fromDate;
    private LocalDate toDate;
    private final Map<String, Integer> responseCodeMap = new HashMap<>();
    private final Map<String, Integer> endpointMap = new HashMap<>();
    private final Map<LocalDate, Integer> requestsPerDayMap = new TreeMap<>();

    public List<Path> findFiles(String pattern) throws IOException {
        List<Path> matchesList = new ArrayList<>();
        String rootDir = "src/main/resources/project3/All Logs/";
        PathMatcher matcher =
            FileSystems.getDefault().getPathMatcher("glob:" + rootDir + pattern);

        FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attribs) {
                if (matcher.matches(file)) {
                    matchesList.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(Path.of(rootDir), matcherVisitor);
        return matchesList;
    }

    public void analyzeFile(List<Path> logFiles, LocalDate fromDate, LocalDate toDate, TypeOutputFile typeFile) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        for (Path path : logFiles) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    analyzeLogLine(line);
                }
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        averageResponseSize = (fullResponseSize / countResponse);
        writeStatistic(
            typeFile,
            logFiles.stream().map(Path::toString).toList()
        );
    }

    public void analyzeULR(String url, LocalDate fromDate, LocalDate toDate, TypeOutputFile typeFile)
        throws IOException, InterruptedException, ParseException {

        this.fromDate = fromDate;
        this.toDate = toDate;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        HttpResponse<String> response;
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        BufferedReader reader = new BufferedReader(new StringReader(response.body()));
        String line;
        while ((line = reader.readLine()) != null) {
            analyzeLogLine(line);
        }
        averageResponseSize = (fullResponseSize / countResponse);
        writeStatistic(
            typeFile,
            Collections.singletonList(url)
        );
    }

    private void analyzeLogLine(String line) throws ParseException {
        LogEntry log = new LogEntry(line);
        if (log.getDateTime().toLocalDate().isEqual(toDate) || log.getDateTime().toLocalDate().isEqual(fromDate)
            ||
            (log.getDateTime().toLocalDate().isAfter(fromDate) && log.getDateTime().toLocalDate().isBefore(toDate))) {
            ++countResponse;
            fullResponseSize += log.getResponseSize();

            if (endpointMap.containsKey(log.getEndpoint())) {
                endpointMap.put(log.getEndpoint(), endpointMap.get(log.getEndpoint()) + 1);
            } else {
                endpointMap.put(log.getEndpoint(), 1);
            }

            String code = Integer.toString(log.getResponseCode());
            if (responseCodeMap.containsKey(code)) {
                responseCodeMap.put(code, responseCodeMap.get(code) + 1);
            } else {
                responseCodeMap.put(code, 1);
            }

            LocalDate date = log.getDateTime().toLocalDate();
            if (requestsPerDayMap.containsKey(date)) {
                requestsPerDayMap.put(date, requestsPerDayMap.get(date) + 1);
            } else {
                requestsPerDayMap.put(date, 1);
            }
        }
    }

    private void writeStatistic(TypeOutputFile typeFile, List<String> logFiles) {
        Statistic statistic;
        String fileName;
        if (typeFile.equals(TypeOutputFile.MARKDOWN)) {
            statistic = new MdStatistic();
            fileName = "src/main/resources/project3/output.md";
        } else {
            statistic = new AdocStatistic();
            fileName = "src/main/resources/project3/output.adoc";
        }

        String generalInformation =
            statistic.makeGeneralInformation(logFiles, fromDate, toDate, countResponse, averageResponseSize);
        String requestedResourcesFrequency = statistic.makeRequestedResourcesFrequency(endpointMap);
        String responseCodeFrequency = statistic.makeResponseCodeFrequency(responseCodeMap);
        String requestsPerDayFrequency = statistic.makeRequestsPerDayFrequency(requestsPerDayMap);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(generalInformation);
            writer.write(requestedResourcesFrequency);
            writer.write(responseCodeFrequency);
            writer.write(requestsPerDayFrequency);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file!");
        }
    }
}

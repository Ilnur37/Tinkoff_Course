package edu.project3.log;

import edu.project3.typeOfStatistic.AdocStatistic;
import edu.project3.typeOfStatistic.MdStatistic;
import edu.project3.typeOfStatistic.Statistic;
import edu.project3.typeOfStatistic.TypeOutputFile;
import edu.project3.utils.CommandLineArg;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import lombok.SneakyThrows;

public class LogAnalyzer {
    private long fullResponseSize = 0;
    private long averageResponseSize = 0;
    private int countResponse = 0;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate tempFromDate;
    private LocalDate tempToDate;
    private final LocalDate fromDateDefault = LocalDate.MIN;
    private final LocalDate toDateDefault = LocalDate.MAX;
    private final Map<String, Integer> responseCodeMap = new HashMap<>();
    private final Map<String, Integer> endpointMap = new HashMap<>();
    private final Map<LocalDate, Integer> requestsPerDayMap = new TreeMap<>();
    private final Map<String, Integer> httpUserAgent = new HashMap<>();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String rootDir = "src/main/resources/project3/All Logs/";

    @SneakyThrows(IOException.class)
    public List<Path> findFiles(String pattern) {
        List<Path> matchesList = new ArrayList<>();
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

    public void analyzeFile(List<Path> logFiles, CommandLineArg commandLineArg) {
        firstInitializationDate(commandLineArg.getFromDate(), commandLineArg.getToDate());
        for (Path path : logFiles) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    analyzeLogLine(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        averageResponseSize = (fullResponseSize / countResponse);
        setDate();
        writeStatistic(
            commandLineArg.getFormatOutput(),
            logFiles.stream().map(Path::toString).toList()
        );
    }

    @SneakyThrows({IOException.class, InterruptedException.class})
    public void analyzeULR(String uri, CommandLineArg commandLineArg) {
        firstInitializationDate(commandLineArg.getFromDate(), commandLineArg.getToDate());

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .build();
        HttpResponse<String> response;
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        BufferedReader reader = new BufferedReader(new StringReader(response.body()));
        String line;
        while ((line = reader.readLine()) != null) {
            analyzeLogLine(line);
        }
        averageResponseSize = (fullResponseSize / countResponse);
        setDate();
        writeStatistic(
            commandLineArg.getFormatOutput(),
            Collections.singletonList(uri)
        );
    }

    private void firstInitializationDate(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = Objects.requireNonNullElse(fromDate, fromDateDefault);
        this.toDate = Objects.requireNonNullElse(toDate, toDateDefault);
    }

    private void analyzeLogLine(String line) {
        LogEntry log = new LogEntry(line);
        LocalDate currDate = log.getDateTime().toLocalDate();

        if (!(currDate.isAfter(fromDate) && currDate.isBefore(toDate))) {
            return;
        }

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

        if (httpUserAgent.containsKey(log.getUserAgent())) {
            httpUserAgent.put(log.getUserAgent(), httpUserAgent.get(log.getUserAgent()) + 1);
        } else {
            httpUserAgent.put(log.getUserAgent(), 1);
        }

        if (tempFromDate == null) {
            tempFromDate = currDate;
        } else {
            if (currDate.isBefore(tempFromDate)) {
                tempFromDate = currDate;
            }
        }
        if (tempToDate == null) {
            tempToDate = currDate;
        } else {
            if (currDate.isAfter(tempToDate)) {
                tempToDate = currDate;
            }
        }
    }

    private void setDate() {
        if (fromDate == fromDateDefault) {
            fromDate = tempFromDate;
        }
        if (toDate == toDateDefault) {
            toDate = tempToDate;
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
        String requestResourcesFrequency = statistic.makeRequestResourcesFrequency(endpointMap);
        String responseCodeFrequency = statistic.makeResponseCodeFrequency(responseCodeMap);
        String requestsPerDayFrequency = statistic.makeRequestsPerDayFrequency(requestsPerDayMap);
        String httpUserAgentFrequency = statistic.makeHttpUserAgentFrequency(httpUserAgent);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(generalInformation);
            writer.write(requestResourcesFrequency);
            writer.write(responseCodeFrequency);
            writer.write(requestsPerDayFrequency);
            writer.write(httpUserAgentFrequency);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file!");
        }
    }

    public long getAverageResponseSize() {
        return averageResponseSize;
    }

    public int getCountResponse() {
        return countResponse;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Map<String, Integer> getResponseCodeMap() {
        return responseCodeMap;
    }

    public Map<String, Integer> getEndpointMap() {
        return endpointMap;
    }

    public Map<LocalDate, Integer> getRequestsPerDayMap() {
        return requestsPerDayMap;
    }
}

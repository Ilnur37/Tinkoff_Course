package edu.project3.typeOfStatistic;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SuppressWarnings("MultipleStringLiterals")
public class MdStatistic implements Statistic {

    public MdStatistic() {

    }

    @Override
    public String makeGeneralInformation(
        List<String> logFiles,
        LocalDate fromDate,
        LocalDate toDate,
        int countResponse,
        long averageResponseSize
    ) {
        return ""
            + "# Общая информация\n"
            + "|Метрика|Значение|\n"
            + "|---|---|\n"
            + "|Файл(-ы)|" + getPathFiles(logFiles) + "|\n"
            + "|Начальная дата|" + fromDate.toString() + "|\n"
            + "|Конечная дата|" + toDate.toString() + "|\n"
            + "|Количество запросов|" + countResponse + "|\n"
            + "|Средний размер ответа|" + averageResponseSize + "b|\n";
    }

    @Override
    public String getPathFiles(List<String> logFiles) {
        StringBuilder pathFiles = new StringBuilder();
        logFiles.forEach(path -> pathFiles.append("[").append(path).append("]").append(" "));
        return pathFiles.toString();
    }

    @Override
    public String makeRequestResourcesFrequency(Map<String, Integer> endpointMap) {
        StringBuilder requestedResourcesFrequency = new StringBuilder("""
            # Запрашиваемые ресурсы
            |Ресурс|Количество|
            |---|---|
            """);
        for (Map.Entry<String, Integer> entry : endpointMap.entrySet()) {
            requestedResourcesFrequency
                .append(VERTICAL_SLASH).append(entry.getKey())
                .append(VERTICAL_SLASH).append(entry.getValue())
                .append(VERTICAL_SLASH).append("\n");
        }
        return requestedResourcesFrequency.toString();
    }

    @Override
    public String makeResponseCodeFrequency(Map<String, Integer> responseCodeMap) {
        StringBuilder responseCodeFrequency = new StringBuilder("""
            # Код ответа
            |Код|Имя|Количество|
            |---|---|---|
            """);
        for (Map.Entry<String, Integer> entry : responseCodeMap.entrySet()) {
            responseCodeFrequency
                .append(VERTICAL_SLASH).append(entry.getKey())
                .append(VERTICAL_SLASH).append(CODE_ERROR.get(Integer.parseInt(entry.getKey())))
                .append(VERTICAL_SLASH).append(entry.getValue())
                .append(VERTICAL_SLASH).append("\n");
        }
        return responseCodeFrequency.toString();
    }

    @Override
    public String makeRequestsPerDayFrequency(Map<LocalDate, Integer> requestsPerDayMap) {
        StringBuilder requestsPerDayFrequency = new StringBuilder("""
            # Количество запросов за день
            |День|Количество|
            |---|---|
            """);
        for (Map.Entry<LocalDate, Integer> entry : requestsPerDayMap.entrySet()) {
            requestsPerDayFrequency
                .append(VERTICAL_SLASH).append(entry.getKey())
                .append(VERTICAL_SLASH).append(entry.getValue())
                .append(VERTICAL_SLASH).append("\n");
        }
        return requestsPerDayFrequency.toString();
    }

    @Override
    public String makeHttpUserAgentFrequency(Map<String, Integer> httpUserAgent) {
        StringBuilder httpUserAgentFrequency = new StringBuilder("""
            # httpUserAgent
            |Агент|Количество|
            |---|---|
            """);
        for (Map.Entry<String, Integer> entry : httpUserAgent.entrySet()) {
            httpUserAgentFrequency
                .append(VERTICAL_SLASH).append(entry.getKey())
                .append(VERTICAL_SLASH).append(entry.getValue())
                .append(VERTICAL_SLASH).append("\n");
        }
        return httpUserAgentFrequency.toString();
    }
}

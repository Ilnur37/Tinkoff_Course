package edu.project3.typeOfStatistic;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AdocStatistic implements Statistic {

    public AdocStatistic() {

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
            + "== Общая информация\n"
            + TABLE_BORDER_ADOC
            + "|Метрика|Значение\n"
            + "|Файл(-ы)|" + getPathFiles(logFiles) + "\n"
            + "|Начальная дата|" + fromDate.toString() + "\n"
            + "|Конечная дата|" + toDate.toString() + "\n"
            + "|Количество запросов|" + countResponse + "\n"
            + "|Средний размер ответа|" + averageResponseSize + "b\n"
            + TABLE_BORDER_ADOC;
    }

    @Override
    public String getPathFiles(List<String> logFiles) {
        StringBuilder pathFiles = new StringBuilder();
        logFiles.forEach(path -> pathFiles.append("[").append(path).append("]").append(" "));
        return pathFiles.toString();
    }

    @Override
    public String makeRequestResourcesFrequency(Map<String, Integer> endpointMap) {
        StringBuilder requestedResourcesFrequency = new StringBuilder();

        requestedResourcesFrequency
            .append("== Запрашиваемые ресурсы\n")
            .append(TABLE_BORDER_ADOC)
            .append("|Ресурс|Количество\n");

        for (Map.Entry<String, Integer> entry : endpointMap.entrySet()) {
            requestedResourcesFrequency
                .append(VERTICAL_SLASH).append(entry.getKey())
                .append(VERTICAL_SLASH).append(entry.getValue())
                .append("\n");
        }
        requestedResourcesFrequency.append(TABLE_BORDER_ADOC);
        return requestedResourcesFrequency.toString();
    }

    @Override
    public String makeResponseCodeFrequency(Map<String, Integer> responseCodeMap) {
        StringBuilder responseCodeFrequency = new StringBuilder();

        responseCodeFrequency
            .append("== Код ответа\n")
            .append(TABLE_BORDER_ADOC)
            .append("|Код|Имя|Количество\n");

        for (Map.Entry<String, Integer> entry : responseCodeMap.entrySet()) {
            responseCodeFrequency
                .append(VERTICAL_SLASH).append(entry.getKey())
                .append(VERTICAL_SLASH).append(CODE_ERROR.getErrors().get(Integer.parseInt(entry.getKey())))
                .append(VERTICAL_SLASH).append(entry.getValue())
                .append("\n");
        }
        responseCodeFrequency.append(TABLE_BORDER_ADOC);
        return responseCodeFrequency.toString();
    }

    @Override
    public String makeRequestsPerDayFrequency(Map<LocalDate, Integer> requestsPerDayMap) {
        StringBuilder requestsPerDayFrequency = new StringBuilder();

        requestsPerDayFrequency
            .append("== Количество запросов за день\n")
            .append(TABLE_BORDER_ADOC)
            .append("|День|Количество\n");

        for (Map.Entry<LocalDate, Integer> entry : requestsPerDayMap.entrySet()) {
            requestsPerDayFrequency
                .append(VERTICAL_SLASH).append(entry.getKey())
                .append(VERTICAL_SLASH).append(entry.getValue())
                .append("\n");
        }
        requestsPerDayFrequency.append(TABLE_BORDER_ADOC);
        return requestsPerDayFrequency.toString();
    }

    @Override
    public String makeHttpUserAgentFrequency(Map<String, Integer> httpUserAgent) {
        StringBuilder httpUserAgentFrequency = new StringBuilder();

        httpUserAgentFrequency
            .append("== httpUserAgent\n")
            .append(TABLE_BORDER_ADOC)
            .append("|Агент|Количество\n");

        for (Map.Entry<String, Integer> entry : httpUserAgent.entrySet()) {
            httpUserAgentFrequency
                .append(VERTICAL_SLASH).append(entry.getKey())
                .append(VERTICAL_SLASH).append(entry.getValue())
                .append("\n");
        }
        httpUserAgentFrequency.append(TABLE_BORDER_ADOC);
        return httpUserAgentFrequency.toString();
    }
}

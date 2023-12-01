package edu.project3.typeOfStatistic;

import edu.project3.utils.CodeError;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Statistic {
    String VERTICAL_SLASH = "|";
    String TABLE_BORDER_ADOC = "|===\n";
    Map<Integer, String> CODE_ERROR = CodeError.ERRORS;

    String makeGeneralInformation(
        List<String> logFiles,
        LocalDate fromDate,
        LocalDate toDate,
        int countResponse,
        long averageResponseSize
    );

    String getPathFiles(List<String> logFiles);

    String makeRequestResourcesFrequency(Map<String, Integer> endpointMap);

    String makeResponseCodeFrequency(Map<String, Integer> responseCodeMap);

    String makeRequestsPerDayFrequency(Map<LocalDate, Integer> requestsPerDayMap);

    String makeHttpUserAgentFrequency(Map<String, Integer> httpUserAgent);
}

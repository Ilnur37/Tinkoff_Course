package edu.project3.log;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;

@Getter
public class LogEntry {
    private final String ipAddress;
    private final OffsetDateTime dateTime;
    private final String method;
    private final String endpoint;
    private final String protocol;
    private final int responseCode;
    private final long responseSize;
    private final String userAgent;
    private static final String LOG_PATTERN =
        "^(\\S+) - - \\[([^\\]]+)\\] \"([A-Z]+) ([^\\s]+) HTTP/(\\d\\.\\d)\" (\\d+) (\\d+) (\"-\" )*\"(.*)\"$";
    private static final Pattern PATTERN = Pattern.compile(LOG_PATTERN);
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

    @SuppressWarnings("MagicNumber")
    public LogEntry(String logLine) throws ParseException {
        Matcher matcher = PATTERN.matcher(logLine);

        if (matcher.matches()) {
            this.ipAddress = matcher.group(1);
            this.dateTime = OffsetDateTime.parse(matcher.group(2), DATE_TIME_FORMATTER);
            this.method = matcher.group(3);
            this.endpoint = matcher.group(4);
            this.protocol = "HTTP/" + matcher.group(5);
            this.responseCode = Integer.parseInt(matcher.group(6));
            this.responseSize = Long.parseLong(matcher.group(7));
            this.userAgent = matcher.group(8);
        } else {
            throw new ParseException("Log entry does not match expected pattern", 0);
        }
    }
}

package edu.project3;

import edu.project3.log.LogAnalyzer;
import edu.project3.typeOfStatistic.TypeOutputFile;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RunLogAnalyzer {
    private final static String ADOC_FORMAT = "adoc";
    private final static String MD_FORMAT = "markdown";
    private final static String PATH_ARG = "--path";
    private final static String FROM_ARG = "--from";
    private final static String TO_ARG = "--to";
    private final static String FORMAT_ARG = "--format";
    private final static int MAX_SIZE_ARGS = 8;
    private final static int MIN_SIZE_ARGS = 2;

    private final static List<String> FORMAT_OUTPUT = new ArrayList<>(List.of(MD_FORMAT, ADOC_FORMAT));
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static IllegalArgumentException ILLEGAL_ARGUMENT_EXCEPTION =
        new IllegalArgumentException("Invalid arguments were passed when running the program!");

    private final static TypeOutputFile DEFAULT_TYPE_OUTPUT_FILE = TypeOutputFile.MARKDOWN;
    private static TypeOutputFile formatOutput;
    private static LocalDate fromDate = null;
    private static LocalDate toDate = null;

    private RunLogAnalyzer() {

    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        int idx = 0;
        int countArgs = args.length;
        if (countArgs < MIN_SIZE_ARGS
            || countArgs > MAX_SIZE_ARGS
            || countArgs % 2 != 0
            || !Objects.equals(args[idx], PATH_ARG)
        ) {
            throw ILLEGAL_ARGUMENT_EXCEPTION;
        }

        LogAnalyzer logAnalyzer = new LogAnalyzer();
        String sourceOfLogs = args[idx + 1];

        setParameters(args);

        if (sourceOfLogs.startsWith("https://")) {
            logAnalyzer.analyzeULR(sourceOfLogs, fromDate, toDate, formatOutput);
        } else {
            List<Path> logFiles = logAnalyzer.findFiles(sourceOfLogs);
            logAnalyzer.analyzeFile(logFiles, fromDate, toDate, formatOutput);
        }
    }

    private static void setParameters(String[] args) {
        String format = null;
        int idx = 2;
        while (idx < args.length) {
            switch (args[idx]) {
                case FROM_ARG -> {
                    checkingForReEnteringParameter(fromDate);
                    fromDate = LocalDate.parse(args[idx + 1], FORMATTER);
                }
                case TO_ARG -> {
                    checkingForReEnteringParameter(toDate);
                    toDate = LocalDate.parse(args[idx + 1], FORMATTER);
                }
                case FORMAT_ARG -> {
                    checkingForReEnteringParameter(format);
                    format = args[idx + 1];
                    if (!FORMAT_OUTPUT.contains(format)) {
                        throw ILLEGAL_ARGUMENT_EXCEPTION;
                    }
                }
                default -> throw ILLEGAL_ARGUMENT_EXCEPTION;
            }
            idx += 2;
        }
        if (format == null) {
            formatOutput = DEFAULT_TYPE_OUTPUT_FILE;
        } else {
            formatOutput = setFormatOutput(format);
        }
    }

    private static void checkingForReEnteringParameter(Object object) {
        if (object != null) {
            throw ILLEGAL_ARGUMENT_EXCEPTION;
        }
    }

    private static TypeOutputFile setFormatOutput(String format) {
        if (format.equals(MD_FORMAT)) {
            return TypeOutputFile.MARKDOWN;
        } else {
            return TypeOutputFile.ASCIIDOCTOR;
        }
    }
}

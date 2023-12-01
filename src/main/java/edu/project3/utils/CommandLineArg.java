package edu.project3.utils;

import edu.project3.typeOfStatistic.TypeOutputFile;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommandLineArg {
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final TypeOutputFile formatOutput;

}

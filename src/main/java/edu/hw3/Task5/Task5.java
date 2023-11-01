package edu.hw3.Task5;

import java.util.Arrays;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task5 {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String SPLIT = " ";

    private Task5() {

    }

    public static Person[] parseContacts(String[] names, SortingOrder sort) {
        LOGGER.info("Names: " + Arrays.toString(names));
        if (sort == null) {
            throw new NullPointerException("Sort order is null!");
        }
        LOGGER.info("Sort order: " + sort);

        int length = names.length;
        Person[] persons = new Person[length];
        for (int i = 0; i < names.length; i++) {
            validatingName(names[i]);
            persons[i] = new Person(names[i]);
        }

        if (sort.equals(SortingOrder.DESC)) {
            Arrays.sort(persons, Collections.reverseOrder());
        } else {
            Arrays.sort(persons);
        }

        LOGGER.info("Names after sort: " + Arrays.toString(persons));
        return persons;
    }

    private static void validatingName(String fullName) {
        if (fullName == null) {
            throw new IllegalArgumentException("First name last name cannot be null!");
        }
        if (fullName.length() == 0) {
            throw new IllegalArgumentException("First name last name cannot be empty!");
        }

        String[] nameAndSurname = fullName.split(SPLIT);
        if (nameAndSurname.length > 2 || nameAndSurname.length == 0) {
            throw new IllegalArgumentException("The name must consist of 1 or 2 words!");
        }
    }
}

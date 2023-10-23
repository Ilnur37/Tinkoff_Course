package edu.hw3.Task5;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task5 {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String INCREASE = "ASC";
    public static final String DECREASING = "DESC";
    public static final String SPLIT = " ";

    public static Person[] parseContacts(String[] names, String sort) {
        LOGGER.info("Names: " + Arrays.toString(names));
        if (sort == null) {
            throw new NullPointerException("Sort order is null!");
        }
        LOGGER.info("Sort order: " + sort);
        if (!sort.equals(INCREASE) && !sort.equals(DECREASING)) {
            throw new IllegalArgumentException("Incorrect sort order selected!");
        }

        int length = names.length;
        Person[] persons = new Person[length];
        for (int i = 0; i < names.length; i++) {
            String[] nameAndSurname = names[i].split(SPLIT);
            int size = nameAndSurname.length;

            if (size > 2 || size == 0) {
                throw new IllegalArgumentException("The name must consist of 1 or 2 words!");
            }
            if (size == 2) {
                persons[i] = new Person(nameAndSurname[0], nameAndSurname[1]);
            } else {
                persons[i] = new Person(nameAndSurname[0]);
            }
        }
        Arrays.sort(persons);
        /*Arrays.sort(persons, (p1, p2) -> {
            if (p1.getSurname().equals(p2.getSurname())) {
                return p1.getName().compareToIgnoreCase(p2.getName());
            }
            return p1.getSurname().compareToIgnoreCase(p2.getSurname());
        });*/
        if (sort.equals(DECREASING)) {
            persons = arrayReverse(persons);
        }

        LOGGER.info("Names after sort: " + Arrays.toString(persons));
        return persons;
    }

    private static Person[] arrayReverse(Person[] array) {
        Person[] newArray = new Person[array.length];
        int mid = array.length / 2;
        for (int i = 0; i < mid; i++) {
            Person temp = array[i];
            newArray[i] = array[array.length - i - 1];
            newArray[array.length - i - 1] = temp;
        }
        if (mid == 1) {
            newArray[mid] = array[mid];
        }
        return newArray;
    }
}

package edu.hw3.Task5;

import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person> {
    public static final String SPLIT = " ";
    private final String name;
    private String surname;

    public Person(String fullName) {
        String[] nameAndSurname = fullName.split(SPLIT);

        this.name = nameAndSurname[0];
        if (nameAndSurname.length == 2) {
            this.surname = nameAndSurname[1];
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override public String toString() {
        return "Person{"
            + "name='" + name + '\''
            + ", surname='" + surname + '\'' + '}';
    }

    @Override
    public int compareTo(@NotNull Person p) {
        if (this.getSurname() == null || p.getSurname() == null) {
            return this.getName().compareToIgnoreCase(p.getName());
        }
        if (this.getSurname().equals(p.getSurname())) {
            return this.getName().compareToIgnoreCase(p.getName());
        }
        return this.getSurname().compareToIgnoreCase(p.getSurname());
    }
}

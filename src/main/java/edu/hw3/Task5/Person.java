package edu.hw3.Task5;

import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person>{
    private String name;
    private String surname;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public int compareTo(@NotNull Person p) {
        if (this.surname == null || p.surname == null) {
            return this.getName().compareToIgnoreCase(p.getName());
        }
        if (this.getSurname().equals(p.getSurname())) {
            return this.getName().compareToIgnoreCase(p.getName());
        }
        return this.getSurname().compareToIgnoreCase(p.getSurname());
    }
}

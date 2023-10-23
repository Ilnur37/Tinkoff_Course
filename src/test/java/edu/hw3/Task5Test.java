package edu.hw3;

import edu.hw3.Task5.Person;
import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    @DisplayName("Корректные данные 1(ASC)")
    void convertToRoman1() {
        String[] names = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        Person[] persons = new Person[names.length];

        persons[0] = new Person("Thomas", "Aquinas");
        persons[1] = new Person("Rene", "Descartes");
        persons[2] = new Person("David", "Hume");
        persons[3] = new Person("John", "Locke");

        Person[] result = Task5.parseContacts(names, "ASC");
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(persons[i].getName(), result[i].getName());
            Assertions.assertEquals(persons[i].getSurname(), result[i].getSurname());
        }
    }

    @Test
    @DisplayName("Корректные данные 1(DESC)")
    void convertToRoman2() {
        String[] names = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        Person[] persons = new Person[names.length];

        persons[3] = new Person("Thomas", "Aquinas");
        persons[2] = new Person("Rene", "Descartes");
        persons[1] = new Person("David", "Hume");
        persons[0] = new Person("John", "Locke");

        Person[] result = Task5.parseContacts(names, "DESC");
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(persons[i].getName(), result[i].getName());
            Assertions.assertEquals(persons[i].getSurname(), result[i].getSurname());
        }
    }

    @Test
    @DisplayName("Корректные данные 2(DESC)")
    void convertToRoman3() {
        String[] names = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        Person[] persons = new Person[names.length];

        persons[0] = new Person("Carl", "Gauss");
        persons[1] = new Person("Leonhard", "Euler");
        persons[2] = new Person("Paul", "Erdos");

        Person[] result = Task5.parseContacts(names, "DESC");
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(persons[i].getName(), result[i].getName());
            Assertions.assertEquals(persons[i].getSurname(), result[i].getSurname());
        }
    }

    @Test
    @DisplayName("Корректные данные 2(ASC)")
    void convertToRoman4() {
        String[] names = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        Person[] persons = new Person[names.length];

        persons[2] = new Person("Carl", "Gauss");
        persons[1] = new Person("Leonhard", "Euler");
        persons[0] = new Person("Paul", "Erdos");

        Person[] result = Task5.parseContacts(names, "ASC");
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(persons[i].getName(), result[i].getName());
            Assertions.assertEquals(persons[i].getSurname(), result[i].getSurname());
        }
    }

    @Test
    @DisplayName("Фамилии повторяются")
    void convertToRoman_whenSurnamesAreRepeated() {
        String[] names = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Erdos"};
        Person[] persons = new Person[names.length];

        persons[0] = new Person("Carl", "Erdos");
        persons[1] = new Person("Paul", "Erdos");
        persons[2] = new Person("Leonhard", "Euler");

        Person[] result = Task5.parseContacts(names, "ASC");
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(persons[i].getName(), result[i].getName());
            Assertions.assertEquals(persons[i].getSurname(), result[i].getSurname());
        }
    }

    @Test
    @DisplayName("Есть люди без фамилии (ASC)")
    void convertToRoman_whenNoSurnameASC() {
        String[] names = new String[] {"Aohn Aocke", "BThomas", "David Hume", "CRene"};
        Person[] persons = new Person[names.length];

        persons[0] = new Person("Aohn", "Aocke");
        persons[1] = new Person("BThomas");
        persons[2] = new Person("CRene");
        persons[3] = new Person("David", "Hume");

        Person[] result = Task5.parseContacts(names, "ASC");
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(persons[i].getName(), result[i].getName());
            Assertions.assertEquals(persons[i].getSurname(), result[i].getSurname());
        }
    }

    @Test
    @DisplayName("Есть люди без фамилии (DESC)")
    void convertToRoman_whenNoSurnameDESC() {
        String[] names = new String[] {"Aohn Aocke", "BThomas", "David Hume", "CRene"};
        Person[] persons = new Person[names.length];

        persons[3] = new Person("Aohn", "Aocke");
        persons[2] = new Person("BThomas");
        persons[1] = new Person("CRene");
        persons[0] = new Person("David", "Hume");

        Person[] result = Task5.parseContacts(names, "DESC");
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(persons[i].getName(), result[i].getName());
            Assertions.assertEquals(persons[i].getSurname(), result[i].getSurname());
        }
    }

    @Test
    @DisplayName("Пустой массив имен")
    void convertToRoman_whenEmptyNames() {
        String[] names = new String[] {};
        Person[] persons = new Person[names.length];

        Person[] result = Task5.parseContacts(names, "DESC");
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(persons[i].getName(), result[i].getName());
            Assertions.assertEquals(persons[i].getSurname(), result[i].getSurname());
        }
    }

    @Test
    @DisplayName("Пустой параметр сортировки")
    void convertToRoman_whenEmptySort() {
        String[] names = new String[] {};

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Task5.parseContacts(names, null);
        }, "Sort order is not null!");

        Assertions.assertEquals("Sort order is null!", thrown.getMessage());
    }

    @Test
    @DisplayName("Некорректный параметр сортировки")
    void convertToRoman_whenInvalidSort() {
        String[] names = new String[] {};

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task5.parseContacts(names, "yfgyg");
        }, "Sort is valid!");

        Assertions.assertEquals("Incorrect sort order selected!", thrown.getMessage());
    }

    @Test
    @DisplayName("Имя состоит из 3 слов")
    void convertToRoman_whenWordsInNameMoreThen2() {
        String[] names = new String[] {"Aohn Aocke Aocke", "BThomas", "David Hume", "CRene"};

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task5.parseContacts(names, "ASC");
        }, "Name is valid!");

        Assertions.assertEquals("The name must consist of 1 or 2 words!", thrown.getMessage());
    }
}

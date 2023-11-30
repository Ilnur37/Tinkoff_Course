package edu.hw7.Task3And3_5.Task3;

import edu.hw7.Task3And3_5.Person;
import edu.hw7.Task3And3_5.PersonDatabase;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;

public class PersonDBSynchronized implements PersonDatabase {
    private final Map<Integer, Person> persons = new HashMap<>();
    private final Map<String, Set<Integer>> personIdByName = new HashMap<>();
    private final Map<String, Set<Integer>> personIdByAddress = new HashMap<>();
    private final Map<String, Set<Integer>> personIdByPhone = new HashMap<>();

    @Override
    public void add(@NonNull Person person) {
        validatePerson(person);
        int id = person.id();
        String name = person.name();
        String address = person.address();
        String phone = person.phoneNumber();

        synchronized (this) {
            persons.put(id, person);

            Set<Integer> tempSetName = new HashSet<>();
            if (personIdByName.containsKey(name)) {
                tempSetName = personIdByName.get(name);
            }
            tempSetName.add(id);
            personIdByName.put(name, tempSetName);

            Set<Integer> tempSetAddress = new HashSet<>();
            if (personIdByAddress.containsKey(address)) {
                tempSetAddress = personIdByAddress.get(address);
            }
            tempSetAddress.add(id);
            personIdByAddress.put(address, tempSetAddress);

            Set<Integer> tempSetPhone = new HashSet<>();
            if (personIdByPhone.containsKey(phone)) {
                tempSetPhone = personIdByPhone.get(phone);
            }
            tempSetPhone.add(id);
            personIdByPhone.put(phone, tempSetPhone);

        }

    }

    @Override
    public synchronized void delete(int id) {
        if (!persons.containsKey(id)) {
            return;
        }
        Person person = persons.get(id);
        String name = person.name();
        String address = person.address();
        String phone = person.phoneNumber();
        persons.remove(id);
        if (personIdByName.containsKey(name)) {
            personIdByName.get(name).remove(id);
            if (personIdByName.get(name).size() == 0) {
                personIdByName.remove(name);
            }
        }
        if (personIdByAddress.containsKey(address)) {
            personIdByAddress.get(address).remove(id);
            if (personIdByAddress.get(address).size() == 0) {
                personIdByAddress.remove(address);
            }
        }
        if (personIdByPhone.containsKey(phone)) {
            personIdByPhone.get(phone).remove(id);
            if (personIdByPhone.get(phone).size() == 0) {
                personIdByPhone.remove(phone);
            }
        }

    }

    @Override
    public synchronized List<Person> findByName(@NonNull String name) {
        if (!personIdByName.containsKey(name)) {
            return Collections.emptyList();
        }
        return personIdByName.get(name).stream()
            .map(persons::get)
            .toList();
    }

    @Override
    public synchronized List<Person> findByAddress(@NonNull String address) {
        if (!personIdByAddress.containsKey(address)) {
            return Collections.emptyList();
        }
        return personIdByAddress.get(address).stream()
            .map(persons::get)
            .toList();

    }

    @Override
    public synchronized List<Person> findByPhone(@NonNull String phone) {
        if (!personIdByPhone.containsKey(phone)) {
            return Collections.emptyList();
        }
        return personIdByPhone.get(phone).stream()
            .map(persons::get)
            .toList();

    }

    private void validatePerson(Person person) {
        if (persons.containsKey(person.id())) {
            throw new IllegalArgumentException("Person with this ID already exists!");
        }
    }
}

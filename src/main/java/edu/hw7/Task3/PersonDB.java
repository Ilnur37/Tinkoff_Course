package edu.hw7.Task3;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;

public class PersonDB implements PersonDatabase {
    public final Map<Integer, Person> persons = new HashMap<>();
    public final Map<String, Set<Integer>> mapByName = new HashMap<>();
    public final Map<String, Set<Integer>> mapByAddress = new HashMap<>();
    public final Map<String, Set<Integer>> mapByPhone = new HashMap<>();

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
            if (mapByName.containsKey(name)) {
                tempSetName = mapByName.get(name);
            }
            tempSetName.add(id);
            mapByName.put(name, tempSetName);

            Set<Integer> tempSetAddress = new HashSet<>();
            if (mapByAddress.containsKey(address)) {
                tempSetAddress = mapByAddress.get(address);
            }
            tempSetAddress.add(id);
            mapByAddress.put(address, tempSetAddress);

            Set<Integer> tempSetPhone = new HashSet<>();
            if (mapByPhone.containsKey(phone)) {
                tempSetPhone = mapByPhone.get(phone);
            }
            tempSetPhone.add(id);
            mapByPhone.put(phone, tempSetPhone);

        }

    }

    @Override
    public void delete(int id) {
        if (!persons.containsKey(id)) {
            throw new IllegalArgumentException("Person with this id does not exist!");
        }
        Person person = persons.get(id);
        String name = person.name();
        String address = person.address();
        String phone = person.phoneNumber();
        synchronized (this) {
            persons.remove(id);
            if (mapByName.containsKey(name)) {
                mapByName.get(name).remove(id);
                if (mapByName.get(name).size() == 0) {
                    mapByName.remove(name);
                }
            }
            if (mapByAddress.containsKey(address)) {
                mapByAddress.get(address).remove(id);
                if (mapByAddress.get(address).size() == 0) {
                    mapByAddress.remove(address);
                }
            }
            if (mapByPhone.containsKey(phone)) {
                mapByPhone.get(phone).remove(id);
                if (mapByPhone.get(phone).size() == 0) {
                    mapByPhone.remove(phone);
                }
            }
        }
    }

    @Override
    public List<Person> findByName(@NonNull String name) {
        synchronized (this) {
            if (!mapByName.containsKey(name)) {
                return Collections.emptyList();
            }
            return mapByName.get(name).stream()
                .map(persons::get)
                .toList();
        }
    }

    @Override
    public List<Person> findByAddress(@NonNull String address) {
        synchronized (this) {
            if (!mapByAddress.containsKey(address)) {
                return Collections.emptyList();
            }
            return mapByAddress.get(address).stream()
                .map(persons::get)
                .toList();
        }
    }

    @Override
    public List<Person> findByPhone(@NonNull String phone) {
        synchronized (this) {
            if (!mapByPhone.containsKey(phone)) {
                return Collections.emptyList();
            }
            return mapByPhone.get(phone).stream()
                .map(persons::get)
                .toList();
        }
    }

    private void validatePerson(Person person) {
        if (person.name() == null
            || person.address() == null
            || person.phoneNumber() == null) {
            throw new IllegalArgumentException("Parameters of person can not be null!");
        }
        if (persons.containsKey(person.id())) {
            throw new IllegalArgumentException("Person with this ID already exists!");
        }
    }
}

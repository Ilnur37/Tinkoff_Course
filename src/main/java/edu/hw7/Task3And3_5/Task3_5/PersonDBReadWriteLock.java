package edu.hw7.Task3And3_5.Task3_5;

import edu.hw7.Task3And3_5.Person;
import edu.hw7.Task3And3_5.PersonDatabase;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.NonNull;

public class PersonDBReadWriteLock implements PersonDatabase {
    private final Map<Integer, Person> persons = new HashMap<>();
    private final Map<String, Set<Integer>> mapByName = new HashMap<>();
    private final Map<String, Set<Integer>> mapByAddress = new HashMap<>();
    private final Map<String, Set<Integer>> mapByPhone = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    @Override
    public void add(@NonNull Person person) {
        validatePerson(person);
        int id = person.id();
        String name = person.name();
        String address = person.address();
        String phone = person.phoneNumber();

        try {
            writeLock.lock();
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
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public void delete(int id) {
        Person person;
        try {
            readLock.lock();
            if (!persons.containsKey(id)) {
                return;
            }
            person = persons.get(id);
        } finally {
            readLock.unlock();
        }
        String name = person.name();
        String address = person.address();
        String phone = person.phoneNumber();

        try {
            writeLock.lock();
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
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public List<Person> findByName(@NonNull String name) {
        try {
            readLock.lock();
            if (!mapByName.containsKey(name)) {
                return Collections.emptyList();
            }
            return mapByName.get(name).stream()
                .map(persons::get)
                .toList();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByAddress(@NonNull String address) {
        try {
            readLock.lock();
            if (!mapByAddress.containsKey(address)) {
                return Collections.emptyList();
            }
            return mapByAddress.get(address).stream()
                .map(persons::get)
                .toList();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByPhone(@NonNull String phone) {
        try {
            readLock.lock();
            if (!mapByPhone.containsKey(phone)) {
                return Collections.emptyList();
            }
            return mapByPhone.get(phone).stream()
                .map(persons::get)
                .toList();
        } finally {
            readLock.unlock();
        }
    }

    private void validatePerson(Person person) {
        if (person.name() == null
            || person.address() == null
            || person.phoneNumber() == null) {
            throw new IllegalArgumentException("Parameters of person can not be null!");
        }
        try {
            readLock.lock();
            if (persons.containsKey(person.id())) {
                throw new IllegalArgumentException("Person with this ID already exists!");
            }
        } finally {
            readLock.unlock();
        }

    }
}

package edu.hw7.Task3And3_5.Task3_5;

import edu.hw7.Task3And3_5.Person;
import edu.hw7.Task3And3_5.PersonDatabase;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.NonNull;
import lombok.SneakyThrows;

public class PersonDBReadWriteLock implements PersonDatabase {
    private final Map<Integer, Person> persons = new HashMap<>();
    private final Map<String, Set<Integer>> personIdByName = new HashMap<>();
    private final Map<String, Set<Integer>> personIdByAddress = new HashMap<>();
    private final Map<String, Set<Integer>> personIdByPhone = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private final int waitingTime = 500;

    @Override
    @SneakyThrows(InterruptedException.class)
    public void add(@NonNull Person person) {
        validatePerson(person);
        int id = person.id();
        String name = person.name();
        String address = person.address();
        String phone = person.phoneNumber();

        boolean tryWriteLock = writeLock.tryLock(waitingTime, TimeUnit.MILLISECONDS);
        if (tryWriteLock) {
            try {
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
            } finally {
                writeLock.unlock();
            }
        }
    }

    @Override
    @SneakyThrows(InterruptedException.class)
    public void delete(int id) {
        Person person = null;
        boolean tryReadLock = readLock.tryLock(waitingTime, TimeUnit.MILLISECONDS);
        if (tryReadLock) {
            try {
                if (!persons.containsKey(id)) {
                    return;
                }
                person = persons.get(id);
            } finally {
                readLock.unlock();
            }
        }
        String name = person.name();
        String address = person.address();
        String phone = person.phoneNumber();

        boolean tryWriteLock = writeLock.tryLock(waitingTime, TimeUnit.MILLISECONDS);
        if (tryWriteLock) {
            try {
                persons.remove(id);
                if (personIdByName.containsKey(name)) {
                    personIdByName.get(name).remove(id);
                    if (personIdByName.get(name).isEmpty()) {
                        personIdByName.remove(name);
                    }
                }
                if (personIdByAddress.containsKey(address)) {
                    personIdByAddress.get(address).remove(id);
                    if (personIdByAddress.get(address).isEmpty()) {
                        personIdByAddress.remove(address);
                    }
                }
                if (personIdByPhone.containsKey(phone)) {
                    personIdByPhone.get(phone).remove(id);
                    if (personIdByPhone.get(phone).isEmpty()) {
                        personIdByPhone.remove(phone);
                    }
                }
            } finally {
                writeLock.unlock();
            }
        }
    }

    @Override
    @SneakyThrows(InterruptedException.class)
    public List<Person> findByName(@NonNull String name) {
        boolean tryReadLock = readLock.tryLock(waitingTime, TimeUnit.MILLISECONDS);
        if (tryReadLock) {
            try {
                if (!personIdByName.containsKey(name)) {
                    return Collections.emptyList();
                }
                return personIdByName.get(name).stream()
                    .map(persons::get)
                    .toList();
            } finally {
                readLock.unlock();
            }
        }
        return null;
    }

    @Override
    @SneakyThrows(InterruptedException.class)
    public List<Person> findByAddress(@NonNull String address) {
        boolean tryReadLock = readLock.tryLock(waitingTime, TimeUnit.MILLISECONDS);
        if (tryReadLock) {
            try {
                if (!personIdByAddress.containsKey(address)) {
                    return Collections.emptyList();
                }
                return personIdByAddress.get(address).stream()
                    .map(persons::get)
                    .toList();
            } finally {
                readLock.unlock();
            }
        }
        return null;
    }

    @Override
    @SneakyThrows(InterruptedException.class)
    public List<Person> findByPhone(@NonNull String phone) {
        boolean tryReadLock = readLock.tryLock(waitingTime, TimeUnit.MILLISECONDS);
        if (tryReadLock) {
            try {
                if (!personIdByPhone.containsKey(phone)) {
                    return Collections.emptyList();
                }
                return personIdByPhone.get(phone).stream()
                    .map(persons::get)
                    .toList();
            } finally {
                readLock.unlock();
            }
        }
        return null;
    }

    @SneakyThrows(InterruptedException.class)
    private void validatePerson(Person person) {
        boolean tryReadLock = readLock.tryLock(waitingTime, TimeUnit.MILLISECONDS);
        if (tryReadLock) {
            try {
                if (persons.containsKey(person.id())) {
                    throw new IllegalArgumentException("Person with this ID already exists!");
                }
            } finally {
                readLock.unlock();
            }
        }
    }
}

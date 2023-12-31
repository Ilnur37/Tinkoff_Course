package edu.hw7.Task3And3_5;

import java.util.List;
import lombok.NonNull;

public interface PersonDatabase {
    void add(@NonNull Person person) throws InterruptedException;

    void delete(int id);

    List<Person> findByName(@NonNull String name);

    List<Person> findByAddress(@NonNull String address);

    List<Person> findByPhone(@NonNull String phone);
}

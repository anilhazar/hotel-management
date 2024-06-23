package repository;

import common.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    void save(Person person);
    void update(Person person);
    Optional<Person> findById(Long id);
    List<Person> findAll();
}

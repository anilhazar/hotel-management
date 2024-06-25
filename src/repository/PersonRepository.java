package repository;

import entity.person.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    void save(Person person);
    void update(Person person);

    public Optional<Person> findById(Long id);

    public Optional<Person> findByEmail(String email);
    List<Person> findAll();
}

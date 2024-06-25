package service.impl;

import entity.person.Person;
import repository.PersonRepository;
import repository.impl.PersonRepositoryImpl;
import service.AuthenticationService;
import util.PasswordHasher;

import java.util.Optional;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final PersonRepository personRepository;

    public AuthenticationServiceImpl() {
        this.personRepository = new PersonRepositoryImpl();
    }

    @Override
    public void register(Person person) {
        byte[] salt = PasswordHasher.generateSalt();
        String hashedPassword = PasswordHasher.hashPassword(person.getPassword(), salt);

        person.setSalt(salt);
        person.setPassword(hashedPassword);

        Optional<Person> existingPerson = personRepository.findByEmail(person.getEmail());
        if (existingPerson.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        personRepository.save(person);
    }

    @Override
    public Person login(String email, String password) {
        Optional<Person> person = personRepository.findByEmail(email);
        if (person.isPresent() && PasswordHasher.checkPassword(password, person.get().getPassword(), person.get().getSalt())) {
            return person.get();
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
}

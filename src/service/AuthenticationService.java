package service;

import entity.person.Person;

public interface AuthenticationService {
    public void register(Person person);

    public Person login(String email, String password);

}

package entity.manager;

import entity.person.Person;
import entity.person.Role;

public class Manager extends Person {

    public Manager(String name, String password, String email, int age, Role role, byte[] salts) {
        super(name, password, email, age, role, salts);
    }
}

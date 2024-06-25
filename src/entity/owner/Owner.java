package entity.owner;

import entity.person.Person;
import entity.person.Role;

public class Owner extends Person {
    public Owner(String name, String password, String email, int age, Role role, byte[] salts) {
        super(name, password, email, age, role, salts);
    }

}

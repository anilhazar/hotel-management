package entity.customer;

import entity.person.Person;
import entity.person.Role;

public class Customer extends Person {
    public Customer(String name, String password, String email, int age, Role role, byte[] salts) {
        super(name, password, email, age, role, salts);
    }
}

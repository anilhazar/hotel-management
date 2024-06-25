package entity.customer;

import entity.person.Person;
import entity.person.Role;

public class Customer extends Person {
    public Customer(String name, String password, String email, int age, Role role, byte[] salts) {
        super(name, password, email, age, role, salts);
    }

    public Customer(String name, String password, String email, int age, Role role) {
        super(name, password, email, age, role);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}

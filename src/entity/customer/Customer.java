package entity.customer;
import common.Person;
import common.Role;

public class Customer extends Person {


    public Customer(String name, String email, int age, Role role) {
        super(name, email, age, role);
    }
}

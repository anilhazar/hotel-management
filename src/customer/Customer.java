package customer;
import common.User;
import common.Role;

public class Customer extends User {


    public Customer(String name, String email, int age, Role role) {
        super(name, email, age, role);
    }
}

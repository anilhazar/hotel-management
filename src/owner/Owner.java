package owner;

import common.User;
import common.Role;

public class Owner extends User {
    public Owner(String name, String email, int age, Role role) {
        super(name, email, age, role);
    }
}

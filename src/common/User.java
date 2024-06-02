package common;

public abstract class User {
    private Long id;
    protected String name;
    protected String email;
    protected int age;
    protected Role role;

    public User(String name, String email, int age, Role role) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = role;
    }
}
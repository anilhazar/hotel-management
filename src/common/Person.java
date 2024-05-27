package common;

public abstract class Person {
    private Long id;
    protected String name;
    protected String email;
    protected int age;

    public Person(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
